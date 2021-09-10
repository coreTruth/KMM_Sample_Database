import SwiftUI
import shared

struct ContentView: View {
    struct AppUser: Identifiable {
        var id: Int
        var name: String
    }
    
    let userDatabase = UserDatabase(databaseDriverFactory: DatabaseDriverFactory())
    let kmmStorage = KMMStorage(context: NSObject())
    @State private var name: String = ""
    @State var dbUsers: [User] = []
    @State var appUsers: [AppUser] = []
        
    func updateUsers() {
        self.name = ""

        self.dbUsers = userDatabase.getAllUsers()
        self.appUsers = []
        
        for i in 0..<self.dbUsers.count {
            let dbUser = self.dbUsers[i]
            let appUser = AppUser(id: Int(dbUser.id), name: dbUser.name)
            self.appUsers.append(appUser)
        }
    }
    
    var body: some View {
        VStack(spacing: 10) {
            HStack(spacing: 16) {
                TextField("Enter Name", text: $name)
                    .padding()
                    .border(Color.gray, width: 1.0)
                Button(action: {
                    userDatabase.addUser(name: name)
                    kmmStorage.putLastUser(value: name)
                    updateUsers()
                }) {
                    Text("Add")
                        .padding()
                        .background(Color.purple)
                        .foregroundColor(Color.white)
                }
                Button(action: {
                    userDatabase.addUserMultiple(name: name)
                    kmmStorage.putLastUser(value: name)
                    updateUsers()
                }) {
                    Text("Add 3X")
                        .padding()
                        .background(Color.purple)
                        .foregroundColor(Color.white)
                }
            }.padding()
            
            HStack(spacing: 16) {
                Label("Database Content", systemImage: "book.fill")
                    .font(.title2)
                
                Button(action: {
                    userDatabase.deleteAllUsers()
                    updateUsers()
                }) {
                    Text("Clear")
                        .padding()
                        .background(Color.purple)
                        .foregroundColor(.white)
                }
            }
            
            List(appUsers) { user in
                        HStack() {
                            Text(String(user.id))
                            Text(user.name)
                        }
                    }
            
            Spacer()
        }.onAppear {
            updateUsers()
            self.name = kmmStorage.getLastUser()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

import SwiftUI
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync
import Shared

struct UserListView: View {
    @StateViewModel
    var viewModel = UserListViewModel(
        userRepository: KoinDependencies().userRepository
    )

    var body: some View {
        if !viewModel.objects.isEmpty {
            ScrollView {
                    ForEach(viewModel.objects, id: \.self) { item in
                        UserObject(obj: item)
                    }
            }
        } else {
            Text("No data available")
        }
    }
}

struct UserObject: View {
    let obj: UserObjectListItem

    var body: some View {
            VStack(alignment: .leading, spacing: 4) {
                Text(obj.name)
                    .font(.headline)

                Text(obj.username)
                    .font(.subheadline)

                Text(obj.phone)
                    .font(.caption)
            }
    }
}

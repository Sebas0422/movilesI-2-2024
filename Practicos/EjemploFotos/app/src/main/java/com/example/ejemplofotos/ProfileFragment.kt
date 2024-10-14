import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.marginLeft
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplofotos.ProfileViewModel
import com.example.ejemplofotos.R
import com.example.ejemplofotos.User
import com.example.ejemplofotos.UserAdapter
import com.example.ejemplofotos.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Inicializa el RecyclerView
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Obtén el ViewModel
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        // Observa los cambios en la lista de usuarios
        viewModel.users.observe(viewLifecycleOwner) { users ->
            // Configura el adaptador del RecyclerView cuando haya datos
            userAdapter = UserAdapter(users)
            recyclerView.adapter = userAdapter
        }

        return binding.root
    }
}

package xyz.savvamirzoyan.a3app.features.main

import android.os.Bundle
import android.view.View
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import xyz.savvamirzoyan.a3app.R
import xyz.savvamirzoyan.a3app.base.BaseFragment
import xyz.savvamirzoyan.a3app.databinding.FragmentMainBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvMainText.text = "Hello Text with view binding"
        // Do something else
    }
}

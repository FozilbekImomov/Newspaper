package uz.fozilbekimomov.newspaper.ui.home

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.list.ViewPagerAdapter
import uz.fozilbekimomov.newspaper.ui.base.BaseFragment
import uz.fozilbekimomov.newspaper.ui.homePages.page1.TopHeadLineFragment
import uz.fozilbekimomov.newspaper.ui.homePages.page2.CategoryGroupFragment
import uz.fozilbekimomov.newspaper.ui.homePages.page3.SavedFragment


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


class HomeFragment : BaseFragment(R.layout.fragment_home), HomeContract.View {

    override fun onViewCreate() {


        val fragmentList = arrayListOf<Fragment>(
            TopHeadLineFragment(),
            CategoryGroupFragment(),
            SavedFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        viewpager.adapter = adapter

        TabLayoutMediator(
            tabs,
            viewpager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = getString(R.string.latest_news)
                    }
                    1 -> {
                        tab.text = getString(R.string.category)
                    }
                    2 -> {
                        tab.text = getString(R.string.saved)
                    }
                }
            }).attach()

    }


}
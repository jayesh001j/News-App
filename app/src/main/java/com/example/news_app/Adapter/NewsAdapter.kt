package layout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.news_app.Fragment.AllFragment
import com.example.news_app.Fragment.HealtFragment
import com.example.news_app.Fragment.PopularFragment
import com.example.news_app.Fragment.ScienceFragment


class NewsAdapter  (manager: FragmentManager  ) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {




    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return when (position)// Switchcase

        {
            0 -> PopularFragment()
            1 -> AllFragment()
            2 -> HealtFragment()
            3 -> ScienceFragment()
            else -> PopularFragment()



        }

    }
}
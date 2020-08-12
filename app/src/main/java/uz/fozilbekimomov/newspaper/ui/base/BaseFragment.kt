package uz.fozilbekimomov.newspaper.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager
import dagger.android.support.DaggerFragment


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 7/29/20
 * @project ZarBazar
 */


abstract class BaseFragment(@LayoutRes private val contentLayoutId: Int) : DaggerFragment() {

    val TAG = "JJJJJ:${this::class.simpleName}"


    private val fragments = HashMap<String, BaseFragment>()

    lateinit var manager: FragmentManager

    private var listener: ((String) -> Unit)? = null
    var listenerLastFragment: ((Int) -> Unit)? = null

    @LayoutRes
    var layoutId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        manager = requireActivity().supportFragmentManager
        return inflater.inflate(contentLayoutId, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreate()
    }

    abstract fun onViewCreate()


    fun closeActiveFragment() {
        if (manager.backStackEntryCount > 0) manager.popBackStack()
    }

}
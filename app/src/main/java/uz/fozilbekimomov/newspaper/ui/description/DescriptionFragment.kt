package uz.fozilbekimomov.newspaper.ui.description

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_description.*
import uz.fozilbekimomov.newspaper.BuildConfig
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import uz.fozilbekimomov.newspaper.core.utils.load
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.base.BaseFragment
import uz.fozilbekimomov.newspaper.ui.main.MainActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class DescriptionFragment : BaseFragment(R.layout.fragment_description), DescriptionContract.View {

    private var articleData: ArticleData? = null

    private var isSaved = false

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val viewModel: DescriptionVM by viewModels { viewModelProviderFactory }


    override fun onViewCreate() {
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        articleData = requireArguments().getParcelable("articleData")
        articleData?.mUrlToImage?.let { it1 -> imageNews.load(it1) }
        titleFragment.text = articleData?.mSource?.mName
        titleNews.text = articleData?.mTitle
        descriptionNews.text = articleData?.mDescription
        contentNews.text = articleData?.mContent?.let {
            HtmlCompat.fromHtml(
                it,
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
        }

        val ymdFormat =
            SimpleDateFormat("yyyy-MM-dd", Locale("ru"))
        val EEEddMMMyyyy =
            SimpleDateFormat("EEE dd-MMM yyyy", Locale("ru"))
        val date = articleData?.mPublishedAt!!.split("T")[0]

        dateShow.text = "${parseDate(date, ymdFormat, EEEddMMMyyyy)}"
        official_site.tag = articleData?.mUrl
        official_site.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(articleData?.mUrl))
            startActivity(browserIntent)
        }

        share.setOnClickListener {
            shareContent()
        }
        viewModel.isSaved(articleData!!)


        save.setOnClickListener {
            articleData?.let {
                if (isSaved) {
                    save.setImageResource(R.drawable.ic_save)
                    viewModel.deleteFromBase(it, requireContext())
                } else {
                    viewModel.saveToBase(it, requireContext())
                    save.setImageResource(R.drawable.ic_saved)
                }

                isSaved = !isSaved

            }
        }

    }

    private fun shareContent() {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "text/html"
        i.flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        val uri = articleData?.mUrl

        i.putExtra(
            Intent.EXTRA_TEXT,
            Html.fromHtml(
                StringBuilder()
                    .append("<html>")
                    .append(uri)
                    .append("<br/>")
                    .append("<br/>")
                    .append("Отправлено с ${getString(R.string.app_name)} Android -https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
                    .append("<br/>")
                    .append("<br/></html>").toString()
            )
        )
        try {
            startActivity(Intent.createChooser(i, getString(R.string.choose_app)))
        } catch (ex: ActivityNotFoundException) {
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
        (activity as MainActivity).hideTopActionBar()
    }


    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).showBottomNavigation()
        (activity as MainActivity).showTopActionBar()
    }

    fun parseDate(
        inputDateString: String?,
        inputDateFormat: SimpleDateFormat,
        outputDateFormat: SimpleDateFormat
    ): String? {
        var date: Date? = null
        var outputDateString: String? = null
        try {
            date = inputDateFormat.parse(inputDateString)
            outputDateString = outputDateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outputDateString
    }

    override fun showMessage(message: String) {
        val snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    override fun savedState(d: Boolean) {
        isSaved = d
        if (isSaved) {
            save.setImageResource(R.drawable.ic_saved)
        } else {
            save.setImageResource(R.drawable.ic_save)
        }
    }
}
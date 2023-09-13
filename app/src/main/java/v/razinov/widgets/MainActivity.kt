package v.razinov.widgets

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.tradingview.lightweightcharts.api.chart.models.color.toIntColor
import com.tradingview.lightweightcharts.api.series.enums.SeriesMarkerPosition
import com.tradingview.lightweightcharts.api.series.enums.SeriesMarkerShape
import com.tradingview.lightweightcharts.api.series.models.LineData
import com.tradingview.lightweightcharts.api.series.models.SeriesMarker
import com.tradingview.lightweightcharts.api.series.models.Time
import com.tradingview.lightweightcharts.view.ChartsView
import kotlinx.coroutines.launch
import v.razinov.widgets.ui.theme.WidgetsTheme

class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WidgetsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val currentScope = rememberCoroutineScope()
                    val pagerState = rememberPagerState { 5 }
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TabRow(
                            selectedTabIndex = pagerState.currentPage
                        ) {
                            repeat(pagerState.pageCount) { index ->
                                Tab(
                                    selected = pagerState.currentPage == index,
                                    onClick = {
                                        currentScope.launch {
                                            pagerState.animateScrollToPage(
                                                index
                                            )
                                        }
                                    }
                                ) {
                                    Text(text = index.toString())
                                }
                            }
                        }
                        HorizontalPager(
                            modifier = Modifier.weight(1f),
                            state = pagerState
                        ) { pageIndex ->
                            when (pageIndex) {
                                0 -> AndroidView(
                                    modifier = Modifier.fillMaxSize(),
                                    factory = ::MyChartView,
                                )

                                else -> Box(modifier = Modifier.fillMaxSize()) {
                                    Text(
                                        modifier = Modifier.align(Alignment.Center),
                                        text = "page: $pageIndex"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private class MyChartView(context: Context) : ChartsView(context) {

        init {
            api.addLineSeries { seriesApi ->
                seriesApi.setData(data)
                seriesApi.setMarkers(markers)
            }
        }

        val data = listOf(
            LineData(time = Time.Utc(timestamp = 1691989200), value = 593.5f),
            LineData(time = Time.Utc(timestamp = 1692003600), value = 594.7f),
            LineData(time = Time.Utc(timestamp = 1692018000), value = 581.8f),
            LineData(time = Time.Utc(timestamp = 1692032400), value = 576.3f),
            LineData(time = Time.Utc(timestamp = 1692075600), value = 582.9f),
            LineData(time = Time.Utc(timestamp = 1692090000), value = 584.7f),
            LineData(time = Time.Utc(timestamp = 1692104400), value = 580.5f),
            LineData(time = Time.Utc(timestamp = 1692118800), value = 574.0f),
            LineData(time = Time.Utc(timestamp = 1692162000), value = 566.2f),
            LineData(time = Time.Utc(timestamp = 1692176400), value = 559.1f),
            LineData(time = Time.Utc(timestamp = 1692190800), value = 564.0f),
            LineData(time = Time.Utc(timestamp = 1692205200), value = 563.0f),
            LineData(time = Time.Utc(timestamp = 1692248400), value = 569.4f),
            LineData(time = Time.Utc(timestamp = 1692262800), value = 563.0f),
            LineData(time = Time.Utc(timestamp = 1692277200), value = 565.0f),
            LineData(time = Time.Utc(timestamp = 1692291600), value = 568.2f),
            LineData(time = Time.Utc(timestamp = 1692334800), value = 568.9f),
            LineData(time = Time.Utc(timestamp = 1692349200), value = 564.1f),
            LineData(time = Time.Utc(timestamp = 1692363600), value = 573.9f),
            LineData(time = Time.Utc(timestamp = 1692378000), value = 575.1f),
            LineData(time = Time.Utc(timestamp = 1692594000), value = 583.1f),
            LineData(time = Time.Utc(timestamp = 1692608400), value = 587.0f),
            LineData(time = Time.Utc(timestamp = 1692622800), value = 583.0f),
            LineData(time = Time.Utc(timestamp = 1692637200), value = 584.6f),
            LineData(time = Time.Utc(timestamp = 1692680400), value = 583.8f),
            LineData(time = Time.Utc(timestamp = 1692694800), value = 583.6f),
            LineData(time = Time.Utc(timestamp = 1692709200), value = 590.6f),
            LineData(time = Time.Utc(timestamp = 1692723600), value = 590.3f),
            LineData(time = Time.Utc(timestamp = 1692766800), value = 588.1f),
            LineData(time = Time.Utc(timestamp = 1692781200), value = 578.4f),
            LineData(time = Time.Utc(timestamp = 1692795600), value = 583.6f),
            LineData(time = Time.Utc(timestamp = 1692810000), value = 575.9f),
            LineData(time = Time.Utc(timestamp = 1692853200), value = 579.9f),
            LineData(time = Time.Utc(timestamp = 1692867600), value = 578.7f),
            LineData(time = Time.Utc(timestamp = 1692882000), value = 582.1f),
            LineData(time = Time.Utc(timestamp = 1692896400), value = 581.6f),
            LineData(time = Time.Utc(timestamp = 1692939600), value = 580.2f),
            LineData(time = Time.Utc(timestamp = 1692954000), value = 583.3f),
            LineData(time = Time.Utc(timestamp = 1692968400), value = 584.3f),
            LineData(time = Time.Utc(timestamp = 1692982800), value = 587.7f),
            LineData(time = Time.Utc(timestamp = 1693198800), value = 588.4f),
            LineData(time = Time.Utc(timestamp = 1693213200), value = 586.7f),
            LineData(time = Time.Utc(timestamp = 1693227600), value = 587.5f),
            LineData(time = Time.Utc(timestamp = 1693242000), value = 587.5f),
            LineData(time = Time.Utc(timestamp = 1693285200), value = 587.1f),
            LineData(time = Time.Utc(timestamp = 1693299600), value = 585.8f),
            LineData(time = Time.Utc(timestamp = 1693314000), value = 586.7f),
            LineData(time = Time.Utc(timestamp = 1693328400), value = 586.5f),
            LineData(time = Time.Utc(timestamp = 1693371600), value = 588.5f),
            LineData(time = Time.Utc(timestamp = 1693386000), value = 590.8f),
            LineData(time = Time.Utc(timestamp = 1693400400), value = 589.3f),
            LineData(time = Time.Utc(timestamp = 1693414800), value = 589.4f),
            LineData(time = Time.Utc(timestamp = 1693458000), value = 594.2f),
            LineData(time = Time.Utc(timestamp = 1693472400), value = 591.3f),
            LineData(time = Time.Utc(timestamp = 1693486800), value = 589.4f),
            LineData(time = Time.Utc(timestamp = 1693501200), value = 590.2f),
            LineData(time = Time.Utc(timestamp = 1693544400), value = 588.0f),
            LineData(time = Time.Utc(timestamp = 1693558800), value = 591.2f),
            LineData(time = Time.Utc(timestamp = 1693573200), value = 593.2f),
            LineData(time = Time.Utc(timestamp = 1693587600), value = 593.0f),
            LineData(time = Time.Utc(timestamp = 1693803600), value = 600.1f),
            LineData(time = Time.Utc(timestamp = 1693818000), value = 600.2f),
            LineData(time = Time.Utc(timestamp = 1693832400), value = 602.1f),
            LineData(time = Time.Utc(timestamp = 1693846800), value = 602.5f),
            LineData(time = Time.Utc(timestamp = 1693890000), value = 608.9f),
            LineData(time = Time.Utc(timestamp = 1693904400), value = 605.8f),
            LineData(time = Time.Utc(timestamp = 1693918800), value = 607.5f),
            LineData(time = Time.Utc(timestamp = 1693933200), value = 607.2f),
            LineData(time = Time.Utc(timestamp = 1693976400), value = 603.7f),
            LineData(time = Time.Utc(timestamp = 1693990800), value = 606.8f),
            LineData(time = Time.Utc(timestamp = 1694005200), value = 609.1f),
            LineData(time = Time.Utc(timestamp = 1694019600), value = 608.8f),
            LineData(time = Time.Utc(timestamp = 1694062800), value = 617.1f),
            LineData(time = Time.Utc(timestamp = 1694077200), value = 606.7f),
            LineData(time = Time.Utc(timestamp = 1694091600), value = 601.7f),
            LineData(time = Time.Utc(timestamp = 1694106000), value = 603.7f),
            LineData(time = Time.Utc(timestamp = 1694149200), value = 597.5f),
            LineData(time = Time.Utc(timestamp = 1694163600), value = 598.0f),
            LineData(time = Time.Utc(timestamp = 1694178000), value = 596.8f),
            LineData(time = Time.Utc(timestamp = 1694192400), value = 595.5f),
            LineData(time = Time.Utc(timestamp = 1694408400), value = 593.3f),
            LineData(time = Time.Utc(timestamp = 1694422800), value = 591.5f),
            LineData(time = Time.Utc(timestamp = 1694437200), value = 589.2f),
            LineData(time = Time.Utc(timestamp = 1694451600), value = 588.4f),
            LineData(time = Time.Utc(timestamp = 1694494800), value = 600.7f),
            LineData(time = Time.Utc(timestamp = 1694509200), value = 598.7f),
            LineData(time = Time.Utc(timestamp = 1694523600), value = 605.6f),
            LineData(time = Time.Utc(timestamp = 1694538000), value = 606.6f),
            LineData(time = Time.Utc(timestamp = 1694581200), value = 607.5f),
            LineData(time = Time.Utc(timestamp = 1694595600), value = 610.0f),
            LineData(time = Time.Utc(timestamp = 1694610000), value = 606.8f),
        )

        val markers = listOf(
            SeriesMarker(
                time = Time.Utc(timestamp = 1694149200),
                position = SeriesMarkerPosition.IN_BAR,
                shape = SeriesMarkerShape.CIRCLE,
                size = 1,
                color = Color.Red.toArgb().toIntColor()
            ),
            SeriesMarker(
                time = Time.Utc(timestamp = 1694178000),
                position = SeriesMarkerPosition.IN_BAR,
                shape = SeriesMarkerShape.CIRCLE,
                size = 1,
                color = Color.Red.toArgb().toIntColor()
            ),
            SeriesMarker(
                time = Time.Utc(timestamp = 1694437200),
                position = SeriesMarkerPosition.IN_BAR,
                shape = SeriesMarkerShape.CIRCLE,
                size = 1,
                color = Color.Red.toArgb().toIntColor()
            ),
            SeriesMarker(
                time = Time.Utc(timestamp = 1694509200),
                position = SeriesMarkerPosition.IN_BAR,
                shape = SeriesMarkerShape.CIRCLE,
                size = 1,
                color = Color.Red.toArgb().toIntColor()
            ),
        )
    }
}
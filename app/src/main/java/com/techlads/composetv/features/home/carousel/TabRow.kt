package com.techlads.composetv.features.home.carousel

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.items
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Tab
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.TabRowDefaults
import androidx.tv.material3.Text
import com.techlads.composetv.R

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SmartTabsListWithData() {
    SmartTabsList(
        smartTabsContent = generateContent(),
        isTab = { it is TabData.Header },
        smartTab = { tab, _ ->
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                text = tab.title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        smartItem = {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                text = it.title,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TextTabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Tab 1", "Tab 2", "Tab 3 with lots of text")
    val items = listOf(
        TabItem(imageDrawable = R.drawable.hero_item),
        TabItem(imageDrawable = R.drawable.boy),
        TabItem(imageDrawable = R.drawable.boy3),
        TabItem(imageDrawable = R.drawable.boy),
        TabItem(imageDrawable = R.drawable.girl),
        TabItem(imageDrawable = R.drawable.ic_auto_awesome_motion),
        TabItem(imageDrawable = R.drawable.ic_repeat),
        TabItem(imageDrawable = R.drawable.boy),
        TabItem(imageDrawable = R.drawable.hero_item)

    )

    Column {
        TabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    content = {
                        Text(
                            text = title,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    onFocus = {}
                )
            }
        }
        TvLazyVerticalGrid(
            columns = TvGridCells.Fixed(6),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items) {
                TvCell(it)
            }
        }
    }
}

@Composable
private fun TvCell(tabItem: TabItem) {
    Column(modifier = Modifier.size(256.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .border(width = 1.dp, color = Color.Blue, shape = RoundedCornerShape(8.dp))
                .padding(2.dp)
        ) {
            val image: Painter = painterResource(id = tabItem.imageDrawable)
            Image(painter = image, contentDescription = "")
        }
    }

}

private fun generateContent(): List<TabData> = buildList {
    repeat(100) {
        if (it % 15 == 0) {
            add(TabData.Header("Header - $it"))
        } else {
            add(TabData.Item("Item - $it"))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SmartTabsListWithData()
}

sealed class TabData(open val title: String) {

    data class Header(override val title: String) : TabData(title = title)

    data class Item(override val title: String) : TabData(title = title)
}
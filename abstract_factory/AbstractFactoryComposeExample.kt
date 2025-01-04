package abstract_factory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Abstract products
interface AppButton {
    @Composable
    fun Render()
}

interface AppTextView {
    @Composable
    fun Render()
}

// Concrete products for Light theme
class LightButton : AppButton {
    @Composable
    override fun Render() {
        Button(
            onClick = { /* Handle click */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Light Button", color = Color.Black)
        }
    }
}

class LightTextView : AppTextView {
    @Composable
    override fun Render() {
        BasicText(
            text = "Light TextView",
            style = TextStyle(color = Color.Black),
            modifier = Modifier.padding(8.dp)
        )
    }
}

// Concrete products for Dark theme
class DarkButton : AppButton {
    @Composable
    override fun Render() {
        Button(
            onClick = { /* Handle click */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Dark Button", color = Color.White)
        }
    }
}

class DarkTextView : AppTextView {
    @Composable
    override fun Render() {
        BasicText(
            text = "Dark TextView",
            style = TextStyle(color = Color.White),
            modifier = Modifier.padding(8.dp)
        )
    }
}

// Abstract factory
abstract class UiFactory {
    abstract fun createButton(): AppButton
    abstract fun createTextView(): AppTextView
}

// Concrete factories
class LightUiFactory : UiFactory() {
    override fun createButton(): AppButton = LightButton()
    override fun createTextView(): AppTextView = LightTextView()
}

class DarkUiFactory : UiFactory() {
    override fun createButton(): AppButton = DarkButton()
    override fun createTextView(): AppTextView = DarkTextView()
}

// Example application
class Application(private val factory: UiFactory) {
    @Composable
    fun RenderUI() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(if (factory is LightUiFactory) Color.White else Color.Black)
        ) {
            factory.createButton().Render()
            factory.createTextView().Render()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    val uiFactory: UiFactory = if (false) {
        LightUiFactory()
    } else {
        DarkUiFactory()
    }

    val app = Application(uiFactory)
    app.RenderUI()
}

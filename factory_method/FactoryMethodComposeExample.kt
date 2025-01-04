package factory_method

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

// Note data model
data class Note(
    val type: String,
    val title: String,
    val content: String,
    val items: List<String> = emptyList(),
    val imageUrl: String? = null
)

// Product interface
interface NoteUI {
    @Composable
    fun Render(note: Note)
}

// Concrete products
class TextNoteUI : NoteUI {
    @Composable
    override fun Render(note: Note) {
        Column(modifier = Modifier.padding(16.dp)) {
            BasicText(text = note.title, modifier = Modifier.padding(bottom = 8.dp))
            BasicText(text = note.content)
        }
    }
}

class ImageNoteUI : NoteUI {
    @Composable
    override fun Render(note: Note) {
        Column(modifier = Modifier.padding(16.dp)) {
            BasicText(text = note.title, modifier = Modifier.padding(bottom = 8.dp))

            note.imageUrl?.let { imageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            BasicText(text = note.content)
        }
    }
}

class ChecklistNoteUI : NoteUI {
    @Composable
    override fun Render(note: Note) {
        Column(modifier = Modifier.padding(16.dp)) {
            BasicText(text = note.title, modifier = Modifier.padding(bottom = 8.dp))
            note.items.forEach { item ->
                BasicText(text = "☐ $item", modifier = Modifier.padding(bottom = 4.dp))
            }
        }
    }
}

// Abstract Factory
abstract class NoteUIFactory {
    abstract fun createNoteUI(): NoteUI
}

// Concrete Factories
class TextNoteUIFactory : NoteUIFactory() {
    override fun createNoteUI(): NoteUI {
        return TextNoteUI()
    }
}

class ImageNoteUIFactory : NoteUIFactory() {
    override fun createNoteUI(): NoteUI {
        return ImageNoteUI()
    }
}

class ChecklistNoteUIFactory : NoteUIFactory() {
    override fun createNoteUI(): NoteUI {
        return ChecklistNoteUI()
    }
}

// Example application
class FMApplication(private val factory: NoteUIFactory) {
    @Composable
    fun RenderUI(note: Note) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White)
        ) {
            factory.createNoteUI().Render(note)
        }
    }
}

@Preview(showBackground = true, name = "Text Note Example")
@Composable
fun PreviewTextNote() {
    val note = Note(
        type = "Text",
        title = "Meeting Notes",
        content = "Discuss project timelines and deliverables."
    )

    val uiFactory: NoteUIFactory = TextNoteUIFactory()
    val app = FMApplication(uiFactory)
    app.RenderUI(note)
}

@Preview(showBackground = true, name = "Image Note Example")
@Composable
fun PreviewImageNote() {
    val note = Note(
        type = "Image",
        title = "Trip Ideas",
        content = "Here's a picture of the beach I want to visit!",
        imageUrl = "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=400" // Unsplash beach image
    )

    val uiFactory: NoteUIFactory = ImageNoteUIFactory()
    val app = FMApplication(uiFactory)
    app.RenderUI(note)
}

@Preview(showBackground = true, name = "Checklist Note Example")
@Composable
fun PreviewChecklistNote() {
    val note = Note(
        type = "Checklist",
        title = "Grocery List",
        content = "",
        items = listOf("Milk", "Eggs", "Bread", "Butter")
    )

    val uiFactory: NoteUIFactory = ChecklistNoteUIFactory()
    val app = FMApplication(uiFactory)
    app.RenderUI(note)
}

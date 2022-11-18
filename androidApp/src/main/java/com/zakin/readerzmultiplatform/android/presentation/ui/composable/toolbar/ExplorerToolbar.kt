import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SitesToolbar() {
    TopAppBar() {
        Row(Modifier.padding(15.dp)) {
            Text(text = "Sites", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.secondary)
        }
    }
}
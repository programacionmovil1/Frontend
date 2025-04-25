package com.example.limbusapp
import androidx.compose.foundation.background
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.limbusapp.ui.theme.LimbusAppTheme

class ProfileCompletedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimbusAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileCompletedScreen(
                        onViewPlanClicked = {
                            // Navigate to the plan view (implement later)
                            // val intent = Intent(this, NutritionPlanActivity::class.java)
                            // startActivity(intent)
                        },
                        onConfigRemindersClicked = {
                            // Navigate to reminders configuration (implement later)
                            // val intent = Intent(this, RemindersActivity::class.java)
                            // startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileCompletedScreen(
    onViewPlanClicked: () -> Unit,
    onConfigRemindersClicked: () -> Unit
) {
    val primaryPink = Color(0xFFE91E63)
    val successGreen = Color(0xFF4CAF50)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Success check icon in a green circle
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(successGreen),
            contentAlignment = Alignment.Center
        ) {
            // Check mark icon
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_check),
                contentDescription = "Success",
                tint = Color.White,
                modifier = Modifier.size(60.dp)
            )
        }

        // Success message with larger font
        Text(
            text = "¡Perfil completado!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            textAlign = TextAlign.Center
        )

        // Success subtitle with smaller font
        Text(
            text = "Hemos personalizado un\nplan nutricional para ti",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 40.dp),
            textAlign = TextAlign.Center
        )

        // View plan button with primary color
        Button(
            onClick = onViewPlanClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryPink
            )
        ) {
            Text("Ver mi plan")
        }

        // Configure reminders - outlined button style
        OutlinedButton(
            onClick = onConfigRemindersClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = primaryPink
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                brush = androidx.compose.ui.graphics.SolidColor(primaryPink)
            )
        ) {
            Text("Configurar recordatorios")
        }
    }
}
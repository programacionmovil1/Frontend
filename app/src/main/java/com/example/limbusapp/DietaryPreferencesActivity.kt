package com.example.limbusapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.limbusapp.ui.theme.LimbusAppTheme

class DietaryPreferencesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimbusAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DietaryPreferencesScreen(
                        onNextClicked = {
                            // Navigate to the Health Metrics Activity
                            val intent = Intent(this, HealthMetricsActivity::class.java)
                            startActivity(intent)
                        },
                        onBackClicked = {
                            // Go back to previous screen (RiskFactorsActivity)
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DietaryPreferencesScreen(
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    // Dietary preferences options and their selection state
    val dietaryOptions = listOf(
        "Sin restricciones",
        "Vegetariano",
        "Vegano",
        "Sin gluten",
        "Sin lactosa",
        "Bajo en sodio",
        "Bajo en azúcar",
        "Otro"
    )

    // Only one option can be selected
    var selectedOption by remember { mutableStateOf<String?>(null) }

    val primaryPink = Color(0xFFE91E63)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Added top padding to separate from status bar
        Spacer(modifier = Modifier.height(20.dp))

        // Progress bar - now at 75% for step 3 of 4
        LinearProgressIndicator(
            progress = 0.75f, // Third step of 4 steps
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = primaryPink,
            trackColor = Color.LightGray
        )

        // Left-aligned step indicator
        Text(
            text = "Paso 3 de 4",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            textAlign = TextAlign.Start
        )

        // Title with left alignment and proper spacing
        Text(
            text = "Preferencias alimentarias",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Start
        )

        // Subtitle with proper spacing and left alignment
        Text(
            text = "¿Sigues alguna dieta especial?",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Start
        )

        // Dietary preference radio buttons
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            dietaryOptions.forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    RadioButton(
                        selected = selectedOption == option,
                        onClick = { selectedOption = option },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = primaryPink,
                            unselectedColor = Color.Gray
                        )
                    )

                    Text(
                        text = option,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        // Spacer to push buttons to bottom
        Spacer(modifier = Modifier.weight(1f))

        // Next button - rounded corners matching design
        Button(
            onClick = onNextClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryPink
            )
        ) {
            Text("Siguiente")
        }

        // Back button - outlined style with rounded corners
        OutlinedButton(
            onClick = onBackClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
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
            Text("Atrás")
        }

        // Small bottom padding
        Spacer(modifier = Modifier.height(16.dp))
    }
}
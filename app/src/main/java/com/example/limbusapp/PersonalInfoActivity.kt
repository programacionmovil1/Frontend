package com.example.limbusapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.limbusapp.ui.theme.LimbusAppTheme

class PersonalInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimbusAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonalInfoScreen(
                        onNextClicked = {
                            // Navigate to the RiskFactorsActivity
                            val intent = Intent(this, RiskFactorsActivity::class.java)
                            startActivity(intent)
                        },
                        onBackClicked = {
                            // Go back to previous screen
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfoScreen(
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf<String?>(null) }

    val primaryPink = Color(0xFFE91E63)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Added top padding to separate from status bar
        Spacer(modifier = Modifier.height(20.dp))

        // Progress bar positioned better with proper spacing
        LinearProgressIndicator(
            progress = 0.25f, // First step of 4 steps
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = primaryPink,
            trackColor = Color.LightGray
        )

        // Left-aligned step indicator
        Text(
            text = "Paso 1 de 4",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            textAlign = TextAlign.Start
        )

        // Title with left alignment and proper spacing
        Text(
            text = "Información personal",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Start
        )

        // Subtitle with proper spacing and left alignment
        Text(
            text = "Necesitamos estos datos para personalizar tu experiencia",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Start
        )

        // Full name field - left aligned labels
        Text(
            text = "Nombre completo",
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Start
        )

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            placeholder = { Text("Ej: María Sánchez") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryPink,
                unfocusedBorderColor = Color.LightGray
            ),
            shape = RoundedCornerShape(8.dp)
        )

        // Birth date field - left aligned label
        Text(
            text = "Fecha de nacimiento",
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Start
        )

        OutlinedTextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            placeholder = { Text("DD/MM/AAAA") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryPink,
                unfocusedBorderColor = Color.LightGray
            ),
            shape = RoundedCornerShape(8.dp)
        )

        // Gender selection - left aligned label
        Text(
            text = "Género",
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Start
        )

        // Gender buttons with more subtle styling
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = { selectedGender = "Femenino" },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (selectedGender == "Femenino") Color.White else Color.White,
                    contentColor = if (selectedGender == "Femenino") primaryPink else Color.Black
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = if (selectedGender == "Femenino")
                        androidx.compose.ui.graphics.SolidColor(primaryPink)
                    else
                        androidx.compose.ui.graphics.SolidColor(Color.LightGray)
                )
            ) {
                Text("Femenino")
            }

            OutlinedButton(
                onClick = { selectedGender = "Masculino" },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (selectedGender == "Masculino") Color.White else Color.White,
                    contentColor = if (selectedGender == "Masculino") primaryPink else Color.Black
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = if (selectedGender == "Masculino")
                        androidx.compose.ui.graphics.SolidColor(primaryPink)
                    else
                        androidx.compose.ui.graphics.SolidColor(Color.LightGray)
                )
            ) {
                Text("Masculino")
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
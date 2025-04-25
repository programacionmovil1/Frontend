package com.example.limbusapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

class CreateAccountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimbusAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateAccountScreen(
                        onGoogleSignInClicked = {
                            // Implement Google Sign-In logic
                            Toast.makeText(
                                this,
                                "Google Sign-In no implementado aún",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onEmailSignInClicked = {
                            // Navigate to email/password entry screen
                            val intent = Intent(this, EmailPasswordLoginActivity::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(
    onGoogleSignInClicked: () -> Unit,
    onEmailSignInClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Crear cuenta",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Google Sign-In Button
        Button(
            onClick = onGoogleSignInClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(8.dp)
            ) {
                // In a real app, you would include the Google logo here
                Text(
                    text = "G",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Continuar con Google",
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Email & Password Button
        Button(
            onClick = onEmailSignInClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE91E63) // Pinkish color as in the screenshot
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Email y contraseña",
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Al registrarte, aceptas nuestros Términos y Política de Privacidad",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}
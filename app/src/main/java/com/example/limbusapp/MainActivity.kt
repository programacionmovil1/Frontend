package com.example.limbusapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.limbusapp.ui.theme.LimbusAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimbusAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WelcomeScreen(onStartClicked = {
                        // Launch the CreateAccountActivity instead of institution registration
                        val intent = Intent(this, CreateAccountActivity::class.java)
                        startActivity(intent)
                    }, onLoginClicked = {
                        // Handle login for existing users
                        // You could create a separate LoginActivity for this
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    })
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(onStartClicked: () -> Unit, onLoginClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo - replace R.drawable.logo with your actual logo resource
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Limbus Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "LIMBUS",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Nutrición y bienestar\npersonalizada",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = onStartClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE91E63) // Pink color as in the screenshot
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(
                text = "Comenzar",
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        TextButton(
            onClick = onLoginClicked,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("¿Ya tienes cuenta? Iniciar sesión")
        }
    }
}
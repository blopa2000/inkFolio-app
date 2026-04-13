package com.inkfolio.app.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inkfolio.app.ColorDorado
import com.inkfolio.app.ColorFondo
import com.inkfolio.app.ColorTexto
import com.inkfolio.app.ColorTextoTenue
import com.inkfolio.app.ui.theme.InkFolioTheme

// ─── BotonesScreen ────────────────────────────────────────────────────────────
// Identificadores: btnReservar, btnLlamar, btnCompartir
// Métodos: onClick de cada botón → dispara un Intent del sistema
@Composable
fun BotonesScreen() {

    // Contexto necesario para disparar Intents del sistema Android
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // ── Encabezado ─────────────────────────────────────────────────────────
        Text(
            text       = "Acciones Rápidas",
            fontSize   = 18.sp,
            fontWeight = FontWeight.Bold,
            color      = ColorDorado
        )
        Text(
            text     = "Contáctame directamente",
            fontSize = 12.sp,
            color    = ColorTextoTenue,
            modifier = Modifier.padding(top = 2.dp, bottom = 32.dp)
        )

        // ── Botón 1: Reservar Cita ─────────────────────────────────────────────
        // Evento onClick: abre WhatsApp con mensaje predefinido
        Button(
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://wa.me/573000000000?text=Hola,%20quiero%20reservar%20una%20cita")
                )
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape  = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorDorado,
                contentColor   = Color(0xFF121212)
            )
        ) {
            Text("Reservar Cita", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ── Botón 2: Llamar ────────────────────────────────────────────────────
        // Evento onClick: abre el marcador telefónico con el número predefinido
        OutlinedButton(
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+573000000000"))
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape  = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = ColorTexto),
            border = ButtonDefaults.outlinedButtonBorder
        ) {
            Text("Llamar", fontSize = 15.sp, fontWeight = FontWeight.Medium)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ── Botón 3: Compartir Portafolio ──────────────────────────────────────
        // Evento onClick: abre el selector nativo de Android para compartir texto
        OutlinedButton(
            onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Mira el portafolio de Juan Pablo Agudelo tatuador: https://instagram.com/inkfolio"
                    )
                }
                context.startActivity(Intent.createChooser(intent, "Compartir portafolio"))
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape  = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = ColorTexto),
            border = ButtonDefaults.outlinedButtonBorder
        ) {
            Text("Compartir Portafolio", fontSize = 15.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212, widthDp = 320, heightDp = 500)
@Composable
fun PreviewBotones() {
    InkFolioTheme { BotonesScreen() }
}

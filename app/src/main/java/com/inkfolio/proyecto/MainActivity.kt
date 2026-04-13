package com.inkfolio.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inkfolio.app.ui.screens.*
import com.inkfolio.app.ui.theme.InkFolioTheme
import kotlinx.coroutines.launch

// ─── Paleta de colores global ────────────────────────────────────
val ColorFondo      = Color(0xFF121212)
val ColorMenu       = Color(0xFF1A1A1A)
val ColorSuperficie = Color(0xFF2A2A2A)
val ColorDorado     = Color(0xFFC9A84C)
val ColorTexto      = Color(0xFFFFFFFF)
val ColorTextoGris  = Color(0xFFCCCCCC)
val ColorTextoTenue = Color(0xFF888888)

// ─── Enum ────────────────────────────────────────────────────────────────────
enum class Seccion {
    PERFIL, FOTOS, VIDEO, INICIO, BOTONES
}

// ─── Activity ────────────────────────────────────────────────────────────────
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InkFolioTheme {
                InkFolioApp()
            }
        }
    }
}

// ─── APP PRINCIPAL (AQUÍ ESTÁ EL CAMBIO REAL) ────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InkFolioApp() {

    var seccionActiva by remember { mutableStateOf(Seccion.PERFIL) }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MenuPanel(
                seccionActiva = seccionActiva,
                onSeccionSeleccionada = {
                    seccionActiva = it
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "InkFolio",
                            color = ColorDorado
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                                tint = ColorDorado
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = ColorFondo
                    )
                )
            }
        ) { padding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(ColorFondo)
            ) {
                when (seccionActiva) {
                    Seccion.PERFIL  -> PerfilScreen()
                    Seccion.FOTOS   -> FotosScreen()
                    Seccion.VIDEO   -> VideoScreen()
                    Seccion.INICIO  -> WebScreen()
                    Seccion.BOTONES -> BotonesScreen()
                }
            }
        }
    }
}

// ─── MENÚ ─────────────────────────────────
@Composable
fun MenuPanel(
    seccionActiva: Seccion,
    onSeccionSeleccionada: (Seccion) -> Unit
) {
    val items = listOf(
        Seccion.INICIO  to "Inicio",
        Seccion.PERFIL  to "Perfil",
        Seccion.FOTOS   to "Galeria",
        Seccion.VIDEO   to "Video",
        Seccion.BOTONES to "Redes Sociales"
    )

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(260.dp) // ancho tipo drawer
            .background(ColorMenu)
            .padding(16.dp)
    ) {
        // Logo
        Text(
            text       = "InkFolio",
            fontSize   = 16.sp,
            fontWeight = FontWeight.Bold,
            color      = ColorDorado
        )

        Text(
            text     = "Portafolio de arte",
            fontSize = 11.sp,
            color    = ColorTextoTenue
        )

        Spacer(modifier = Modifier.height(12.dp))

        Divider(color = ColorTextoTenue.copy(alpha = 0.3f))

        Spacer(modifier = Modifier.height(12.dp))

        // Ítems
        items.forEach { (seccion, etiqueta) ->
            val activo = seccionActiva == seccion

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSeccionSeleccionada(seccion) }
                    .background(
                        if (activo) ColorDorado.copy(alpha = 0.12f)
                        else Color.Transparent
                    )
                    .padding(vertical = 12.dp, horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(
                            if (activo) ColorDorado else ColorTextoTenue,
                            CircleShape
                        )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text       = etiqueta,
                    fontSize   = 12.sp,
                    fontWeight = if (activo) FontWeight.Bold else FontWeight.Normal,
                    color      = if (activo) ColorDorado else ColorTexto
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Btn inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(ColorDorado)
                .clickable { onSeccionSeleccionada(Seccion.BOTONES) }
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Reservar Cita",
                color = Color(0xFF121212),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// ─── PREVIEW ────────────────────────────────────────────────────────────────
@Preview(showBackground = true)
@Composable
fun PreviewInkFolioApp() {
    InkFolioTheme {
        InkFolioApp()
    }
}
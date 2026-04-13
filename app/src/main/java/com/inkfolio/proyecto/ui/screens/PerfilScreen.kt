package com.inkfolio.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inkfolio.app.ColorDorado
import com.inkfolio.app.ColorFondo
import com.inkfolio.app.ColorSuperficie
import com.inkfolio.app.ColorTexto
import com.inkfolio.app.ColorTextoGris
import com.inkfolio.app.ColorTextoTenue
import com.inkfolio.app.ui.theme.InkFolioTheme

// ─── Datos del perfil ─────────────────────────────────────────────────────────
private val especialidades = listOf(
    "Realism en blanco y negro",
    "Blackwork y Fineline",
    "Watercolor",
    "Geometric y Minimalist"
)

// ─── PerfilScreen ─────────────────────────────────────────────────────────────
// Identificadores de vistas (Compose): scrollState, Avatar, TextNombre,
// TextUbicacion, TarjetaAcerca, TarjetaEspecialidades, TarjetaEstadisticas
@Composable
fun PerfilScreen() {

    // Variable: estado del scroll vertical
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // ── Avatar circular ────────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .size(84.dp)
                .clip(CircleShape)
                .background(ColorSuperficie)
                .border(2.dp, ColorDorado, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text       = "JP",
                fontSize   = 28.sp,
                color      = ColorDorado,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ── Nombre ─────────────────────────────────────────────────────────────
        Text(
            text       = "Hernan yepes",
            fontSize   = 18.sp,
            fontWeight = FontWeight.Bold,
            color      = ColorTexto
        )

        // ── Subtítulo ──────────────────────────────────────────────────────────
        Text(
            text      = "Artista del Tatuaje · Medellin",
            fontSize  = 11.sp,
            color     = ColorTextoTenue,
            textAlign = TextAlign.Center,
            modifier  = Modifier.padding(top = 2.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        // ── Tarjeta: Acerca de mí ──────────────────────────────────────────────
        TarjetaInfo(titulo = "Acerca de Mí") {
            Text(
                text = "Con más de 5 años de experiencia en el arte del tatuaje, " +
                       "me especializo en diseños personalizados que capturan la esencia " +
                       "y las historias de mis clientes. Formado en artes visuales con " +
                       "participación en convenciones nacionales de tatuaje.",
                fontSize   = 12.sp,
                color      = ColorTextoGris,
                lineHeight = 19.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // ── Tarjeta: Especialidades ────────────────────────────────────────────
        TarjetaInfo(titulo = "Especialidades") {
            especialidades.forEach { esp ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier          = Modifier.padding(vertical = 3.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .background(ColorDorado, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = esp, fontSize = 12.sp, color = ColorTextoGris)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // ── Tarjeta: Estadísticas ──────────────────────────────────────────────
        TarjetaInfo(titulo = "Estadísticas") {
            Row(
                modifier              = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                EstadItem(valor = "5+",   etiqueta = "Años")
                EstadItem(valor = "500+", etiqueta = "Tatuajes")
                EstadItem(valor = "4.9",  etiqueta = "Rating")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

// ─── Componente: tarjeta de sección ──────────────────────────────────────────
@Composable
fun TarjetaInfo(titulo: String, contenido: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape    = RoundedCornerShape(12.dp),
        colors   = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text       = titulo,
                fontSize   = 13.sp,
                fontWeight = FontWeight.Bold,
                color      = ColorDorado
            )
            Spacer(modifier = Modifier.height(8.dp))
            contenido()
        }
    }
}

// ─── Componente: ítem de estadística ─────────────────────────────────────────
@Composable
fun EstadItem(valor: String, etiqueta: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(valor,    fontSize = 22.sp, fontWeight = FontWeight.Bold, color = ColorDorado)
        Text(etiqueta, fontSize = 11.sp, color = ColorTextoTenue)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212, widthDp = 320, heightDp = 600)
@Composable
fun PreviewPerfil() {
    InkFolioTheme { PerfilScreen() }
}

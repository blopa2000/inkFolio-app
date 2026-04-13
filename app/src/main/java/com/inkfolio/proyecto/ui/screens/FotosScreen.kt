package com.inkfolio.app.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

// ─── Modelo de dato ───────────────────────────────────────────────────────────
data class TattooWork(
    val id          : Int,
    val titulo      : String,
    val estilo      : String,
    val descripcion : String
)

// ─── Datos de ejemplo ─────────────────────────────────────────────────────────
val listaTrabajos = listOf(
    TattooWork(1, "Manga japonesa",   "Blackwork",  "Cobertura de brazo completo en blackwork. 3 sesiones, ~18 horas de trabajo con agujas 7M."),
    TattooWork(2, "Retrato realismo", "Realism",    "Retrato hiperrealista en espalda completa. Sombreado con técnica de aguada y degradado suave."),
    TattooWork(3, "Floral fine line", "Fine Line",  "Composición floral en costado. Trazos milimétricos realizados con agujas 3RL de precisión."),
    TattooWork(4, "Mandala",          "Geometric",  "Mandala geométrica en antebrazo. Diseño personalizado con simetría trazada a mano alzada."),
    TattooWork(5, "Acuarela",         "Watercolor", "Mariposa en acuarela sobre omóplato. Colores vibrantes aplicados sin contorno negro."),
    TattooWork(6, "Lettering",        "Lettering",  "Frase en script inglés sobre antebrazo. Tipografía personalizada dibujada a mano alzada.")
)

// ─── FotosScreen ──────────────────────────────────────────────────────────────
// Identificadores: trabajoSeleccionado (estado), LazyColumn, TattooWorkCard
// Métodos: onClick (seleccionar/deseleccionar obra)
@Composable
fun FotosScreen() {

    // Variable de estado: obra actualmente seleccionada (null = ninguna)
    var trabajoSeleccionado by remember { mutableStateOf<TattooWork?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
    ) {

        // ── Encabezado ─────────────────────────────────────────────────────────
        Column(modifier = Modifier.padding(start = 14.dp, top = 14.dp, end = 14.dp, bottom = 6.dp)) {
            Text(
                text       = "Galería de Trabajos",
                fontSize   = 17.sp,
                fontWeight = FontWeight.Bold,
                color      = ColorDorado
            )
            Text(
                text     = "Toca una obra para ver su descripción",
                fontSize = 11.sp,
                color    = ColorTextoTenue
            )
        }

        // ── Descripción de la obra seleccionada ────────────────────────────────
        // Solo se muestra si hay una obra seleccionada (trabajoSeleccionado != null)
        trabajoSeleccionado?.let { obra ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 4.dp),
                shape  = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1C2A1A)),
                border = BorderStroke(1.dp, ColorDorado.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text       = obra.titulo,
                        fontSize   = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color      = ColorDorado
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text       = obra.descripcion,
                        fontSize   = 11.sp,
                        color      = ColorTextoGris,
                        lineHeight = 17.sp
                    )
                }
            }
        }

        // ── Lista de obras (LazyColumn = scroll virtualizado) ──────────────────
        LazyColumn(
            modifier            = Modifier.fillMaxSize(),
            contentPadding      = PaddingValues(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = listaTrabajos,
                key   = { it.id }              // clave única para recomposición eficiente
            ) { obra ->
                TattooWorkCard(
                    obra       = obra,
                    seleccionado = trabajoSeleccionado?.id == obra.id,
                    onClick    = {
                        // Método: alternar selección
                        trabajoSeleccionado = if (trabajoSeleccionado?.id == obra.id) null else obra
                    }
                )
            }
        }
    }
}

// ─── Componente: tarjeta de obra ──────────────────────────────────────────────
@Composable
fun TattooWorkCard(
    obra         : TattooWork,
    seleccionado : Boolean,
    onClick      : () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape  = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        border = if (seleccionado) BorderStroke(1.5.dp, ColorDorado) else null
    ) {
        Row(
            modifier          = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Thumbnail placeholder (reemplazar con AsyncImage de Coil si hay fotos reales)
            Box(
                modifier         = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ColorSuperficie),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text       = obra.estilo.take(2).uppercase(),
                    fontSize   = 14.sp,
                    color      = ColorDorado,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(obra.titulo, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = ColorTexto)
                Spacer(modifier = Modifier.height(2.dp))
                Text(obra.estilo, fontSize = 11.sp, color = ColorDorado)
                Text("Toca para ver descripción", fontSize = 10.sp, color = ColorTextoTenue)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212, widthDp = 320, heightDp = 600)
@Composable
fun PreviewFotos() {
    InkFolioTheme { FotosScreen() }
}

package com.inkfolio.app.ui.screens

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
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
import androidx.compose.ui.viewinterop.AndroidView
import com.inkfolio.app.ColorDorado
import com.inkfolio.app.ColorFondo
import com.inkfolio.app.ColorSuperficie
import com.inkfolio.app.ColorTexto
import com.inkfolio.app.ColorTextoTenue
import com.inkfolio.app.ui.theme.InkFolioTheme

// ─── Modelo de dato ───────────────────────────────────────────────────────────
data class VideoItem(
    val id          : Int,
    val titulo      : String,
    val descripcion : String,
    val url         : String
)

// ─── Datos de ejemplo ─────────────────────────────────────────────────────────
val listaVideos = listOf(
    VideoItem(1, "Proceso tatuaje realista",
        "Mira cómo creamos un retrato realista paso a paso.",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
    VideoItem(2, "Técnicas de sombreado",
        "Aprende las técnicas profesionales de sombreado.",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"),
    VideoItem(3, "Diseño de mandala",
        "Proceso completo de un mandala geométrico personalizado.",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4")
)

// ─── VideoScreen ──────────────────────────────────────────────────────────────
// Identificadores: videoActivo (estado), AndroidView (VideoView), LazyColumn
// Métodos: setVideoURI, setMediaController, setOnPreparedListener, stopPlayback
@Composable
fun VideoScreen() {

    // Variable de estado: video que está reproduciéndose actualmente
    var videoActivo by remember { mutableStateOf(listaVideos.first()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
    ) {

        // ── Encabezado ─────────────────────────────────────────────────────────
        Column(modifier = Modifier.padding(start = 14.dp, top = 14.dp, end = 14.dp, bottom = 8.dp)) {
            Text(
                text       = "Videos del Proceso",
                fontSize   = 17.sp,
                fontWeight = FontWeight.Bold,
                color      = ColorDorado
            )
            Text(
                text     = "Explora el arte detrás de cada tatuaje",
                fontSize = 11.sp,
                color    = ColorTextoTenue
            )
        }

        // ── Reproductor de video ───────────────────────────────────────────────
        // AndroidView: puente entre Compose y las vistas clásicas de Android
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .padding(horizontal = 14.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Black),
            factory = { ctx ->
                // Declaración del VideoView y sus propiedades
                VideoView(ctx).apply {
                    // Vinculo: asigna el URI del video activo
                    setVideoURI(Uri.parse(videoActivo.url))
                    // Controles de reproducción: play, pause, seek, volumen
                    val mediaController = MediaController(ctx)
                    mediaController.setAnchorView(this)
                    setMediaController(mediaController)
                    // Evento: reproducir automáticamente cuando el video está listo
                    setOnPreparedListener { mp -> mp.start() }
                }
            },
            // update se ejecuta cada vez que videoActivo cambia de valor
            update = { videoView ->
                videoView.stopPlayback()
                videoView.setVideoURI(Uri.parse(videoActivo.url))
                videoView.start()
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ── Título y descripción del video activo ──────────────────────────────
        Column(modifier = Modifier.padding(horizontal = 14.dp)) {
            Text(
                text       = videoActivo.titulo,
                fontSize   = 14.sp,
                fontWeight = FontWeight.Bold,
                color      = ColorTexto
            )
            Text(
                text     = videoActivo.descripcion,
                fontSize = 11.sp,
                color    = ColorTextoTenue
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // ── Lista de videos disponibles ────────────────────────────────────────
        LazyColumn(
            modifier            = Modifier.fillMaxSize(),
            contentPadding      = PaddingValues(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(items = listaVideos, key = { it.id }) { video ->
                VideoItemCard(
                    video  = video,
                    activo = videoActivo.id == video.id,
                    onClick = { videoActivo = video }   // Método: cambiar video activo
                )
            }
        }
    }
}

// ─── Componente: tarjeta de video ────────────────────────────────────────────
@Composable
fun VideoItemCard(video: VideoItem, activo: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape  = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        border = if (activo) BorderStroke(1.5.dp, ColorDorado) else null
    ) {
        Row(
            modifier          = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier         = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ColorSuperficie),
                contentAlignment = Alignment.Center
            ) {
                Text("▶", fontSize = 20.sp, color = ColorDorado)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(video.titulo,      fontSize = 12.sp, fontWeight = FontWeight.Medium, color = ColorTexto)
                Text(video.descripcion, fontSize = 10.sp, color = ColorTextoTenue, maxLines = 1)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212, widthDp = 320, heightDp = 600)
@Composable
fun PreviewVideo() {
    InkFolioTheme { VideoScreen() }
}

package com.inkfolio.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inkfolio.app.*
import com.inkfolio.app.ui.theme.InkFolioTheme

// ─── DATA: publicaciones de ejemplo ───────────────────────────────────────────
data class Post(
    val imagen: Int,
    val titulo: String,
    val estilo: String,
    val descripcion: String
)

// ⚠️ IMPORTANTE: luego reemplazas estas imágenes por tus tattoos reales
val posts = listOf(
    Post(
        imagen = android.R.drawable.ic_menu_gallery,
        titulo = "Blackwork Rosa",
        estilo = "Blackwork",
        descripcion = "Diseño floral con líneas sólidas y sombreado profundo."
    ),
    Post(
        imagen = android.R.drawable.ic_menu_gallery,
        titulo = "Dragón Japonés",
        estilo = "Oriental",
        descripcion = "Composición dinámica con detalles tradicionales japoneses."
    ),
    Post(
        imagen = android.R.drawable.ic_menu_gallery,
        titulo = "Calavera Realista",
        estilo = "Realismo",
        descripcion = "Sombras suaves y alto nivel de detalle en textura ósea."
    )
)

// ─── SCREEN PRINCIPAL ────────────────────────────────────────────────────────
@Composable
fun WebScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
            .padding(12.dp)
    ) {

        // ── Encabezado ───────────────────────────────────────────────────────
        Text(
            text = "Portafolio",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = ColorDorado
        )

        Text(
            text = "Mis últimos trabajos",
            fontSize = 12.sp,
            color = ColorTextoTenue
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ── LISTA DE POSTS ───────────────────────────────────────────────────
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(posts) { post ->
                PostCard(post)
            }
        }
    }
}

// ─── CARD DE PUBLICACIÓN ─────────────────────────────────────────────────────
@Composable
fun PostCard(post: Post) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(ColorSuperficie)
            .padding(10.dp)
    ) {

        // Imagen
        Image(
            painter = painterResource(id = post.imagen),
            contentDescription = post.titulo,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Título
        Text(
            text = post.titulo,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = ColorTexto
        )

        // Estilo
        Text(
            text = post.estilo,
            fontSize = 11.sp,
            color = ColorDorado
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Descripción
        Text(
            text = post.descripcion,
            fontSize = 11.sp,
            color = ColorTextoTenue
        )
    }
}

// ─── PREVIEW ─────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun PreviewWeb() {
    InkFolioTheme {
        WebScreen()
    }
}
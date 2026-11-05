package com.example.controlgastos.interfaz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistorialScreen(historial: List<Movimiento>, onVolver: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Historial Real", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = onVolver) {
                Text("Volver")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        if (historial.isEmpty()) {
            Text("Aún no hay movimientos registrados.")
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(historial.reversed()) { mov ->
                    ItemHistorial(mov)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun ItemHistorial(movimiento: Movimiento) {
    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$ ${movimiento.cantidad}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = if (movimiento.esIngreso) "↑" else "↓",
                fontSize = 24.sp
            )

            Column(horizontalAlignment = Alignment.End) {
                Text(text = movimiento.categoria, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = movimiento.descripcion, fontSize = 12.sp)
            }
        }
    }
}
package com.example.controlgastos.interfaz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GestionDeGastosScreen(
    categorias: List<String>,
    onGuardarMovimiento: (Movimiento) -> Unit,
    onVolver: () -> Unit
) {
    var cantidad by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var categoriaSeleccionada by remember { mutableStateOf("") }
    var esIngreso by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Registro", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = onVolver) { Text("Volver") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            OutlinedButton(onClick = { esIngreso = true }) {
                Text(text = if (esIngreso) "✅ INGRESO" else "Ingreso")
            }
            OutlinedButton(onClick = { esIngreso = false }) {
                Text(text = if (!esIngreso) "✅ EGRESO" else "Egreso")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text("Cantidad ($)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción breve") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Selecciona una categoría:", fontWeight = FontWeight.Bold)

        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()) {
            items(categorias) { cat ->
                Row(
                    modifier = Modifier.fillMaxWidth().clickable { categoriaSeleccionada = cat }.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = categoriaSeleccionada == cat,
                        onCheckedChange = { categoriaSeleccionada = cat }
                    )
                    Text(text = cat, fontSize = 16.sp)
                }
            }
        }

        OutlinedButton(
            onClick = {
                val valor = cantidad.toDoubleOrNull()
                if (valor != null && categoriaSeleccionada.isNotEmpty()) {
                    val nuevoMovimiento = Movimiento(valor, categoriaSeleccionada, descripcion, esIngreso)
                    onGuardarMovimiento(nuevoMovimiento)
                    onVolver()
                }
            },
            modifier = Modifier.width(180.dp).padding(bottom = 16.dp)
        ) {
            Text("Guardar", fontSize = 18.sp)
        }
    }
}

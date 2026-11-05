package com.example.controlgastos.interfaz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Movimiento(
    val cantidad: Double,
    val categoria: String,
    val descripcion: String,
    val esIngreso: Boolean
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White,
                    contentColor = Color.Black
                ) {
                    AppNavegacion()
                }
            }
        }
    }
}

@Composable
fun AppNavegacion() {
    var pantallaActual by remember { mutableStateOf("menu") }

    var categorias by remember { mutableStateOf(listOf("Salario", "Transporte", "Comida", "Ahorro")) }
    var historial by remember { mutableStateOf(listOf<Movimiento>()) }

    when (pantallaActual) {
        "menu" -> HomeScreen(
            historial = historial,
            onIrACategorias = { pantallaActual = "categorias" },
            onIrAGastos = { pantallaActual = "gastos" },
            onIrAHistorial = { pantallaActual = "historial" },
            onIrAResumen = { pantallaActual = "resumen" }
        )
        "categorias" -> GestionDeCategoriasScreen(
            categoriasActuales = categorias,
            onAgregarCategoria = { nueva -> categorias = categorias + nueva },
            onVolver = { pantallaActual = "menu" }
        )
        "gastos" -> GestionDeGastosScreen(
            categorias = categorias,
            onGuardarMovimiento = { nuevo -> historial = historial + nuevo },
            onVolver = { pantallaActual = "menu" }
        )
        "historial" -> HistorialScreen(
            historial = historial,
            onVolver = { pantallaActual = "menu" }
        )
        "resumen" -> ResumenScreen(
            historial = historial,
            onVolver = { pantallaActual = "menu" }
        )
    }
}

@Composable
fun HomeScreen(
    historial: List<Movimiento>,
    onIrACategorias: () -> Unit,
    onIrAGastos: () -> Unit,
    onIrAHistorial: () -> Unit,
    onIrAResumen: () -> Unit
) {
    var isBalanceVisible by remember { mutableStateOf(false) }

    var saldoReal = 0.0
    for (mov in historial) {
        if (mov.esIngreso) {
            saldoReal += mov.cantidad
        } else {
            saldoReal -= mov.cantidad
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Balance", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isBalanceVisible) "$ $saldoReal" else "$ XXXXX",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { isBalanceVisible = !isBalanceVisible }) {
                        Text(text = "👁️", fontSize = 20.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedButton(onClick = onIrACategorias, modifier = Modifier.fillMaxWidth()) {
            Text("Crear Categorías", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = onIrAGastos, modifier = Modifier.fillMaxWidth()) {
            Text("Registrar Movimiento", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = onIrAHistorial, modifier = Modifier.fillMaxWidth()) {
            Text("Historial de movimientos", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = onIrAResumen, modifier = Modifier.fillMaxWidth()) {
            Text("Resumen (Gráficas)", fontSize = 16.sp)
        }
    }
}
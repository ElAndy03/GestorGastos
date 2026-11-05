package com.example.controlgastos.interfaz

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResumenScreen(historial: List<Movimiento>, onVolver: () -> Unit) {

    val gastosPorCategoria = mutableMapOf<String, Double>()
    val ingresosPorCategoria = mutableMapOf<String, Double>()

    for (mov in historial) {
        if (mov.esIngreso) {
            val actual = ingresosPorCategoria[mov.categoria] ?: 0.0
            ingresosPorCategoria[mov.categoria] = actual + mov.cantidad
        } else {
            val actual = gastosPorCategoria[mov.categoria] ?: 0.0
            gastosPorCategoria[mov.categoria] = actual + mov.cantidad
        }
    }

    val paletaColores = listOf(Color.Red, Color.Yellow, Color.Green, Color.Blue, Color.Cyan, Color.Magenta)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Resumen", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = onVolver) {
                Text("Volver")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Desglose de Gastos", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                if (gastosPorCategoria.isEmpty()) {
                    Text("No hay gastos registrados.")
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        GraficaPastel(
                            modifier = Modifier.height(120.dp).width(120.dp),
                            cantidadSecciones = gastosPorCategoria.size,
                            colores = paletaColores
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Column {
                            var colorIndex = 0
                            for ((categoria, total) in gastosPorCategoria) {
                                val colorUsado = paletaColores[colorIndex % paletaColores.size]
                                ItemLeyenda(colorUsado, "$categoria ($$total)")
                                colorIndex++
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Desglose de Ingresos", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                if (ingresosPorCategoria.isEmpty()) {
                    Text("No hay ingresos registrados.")
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        GraficaPastel(
                            modifier = Modifier.height(120.dp).width(120.dp),
                            cantidadSecciones = ingresosPorCategoria.size,
                            colores = paletaColores
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Column {
                            var colorIndex = 0
                            for ((categoria, total) in ingresosPorCategoria) {
                                val colorUsado = paletaColores[colorIndex % paletaColores.size]
                                ItemLeyenda(colorUsado, "$categoria ($$total)")
                                colorIndex++
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GraficaPastel(modifier: Modifier, cantidadSecciones: Int, colores: List<Color>) {
    Canvas(modifier = modifier) {
        var anguloInicio = 0f
        val anguloPorcion = 360f / cantidadSecciones

        for (i in 0 until cantidadSecciones) {
            drawArc(
                color = colores[i % colores.size],
                startAngle = anguloInicio,
                sweepAngle = anguloPorcion,
                useCenter = true
            )
            anguloInicio += anguloPorcion
        }
    }
}

@Composable
fun ItemLeyenda(color: Color, texto: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Canvas(modifier = Modifier.width(16.dp).height(16.dp)) {
            drawRect(color = color)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = texto, fontSize = 14.sp)
    }
}

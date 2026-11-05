package com.example.controlgastos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.controlgastos.data.db.ModeloGastos
import com.example.controlgastos.data.model.Categoria
import com.example.controlgastos.data.model.Gasto
import com.example.controlgastos.interfaz.AppNavegacion
import com.example.controlgastos.ui.theme.ControlgastosTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


data class Movimiento(
    val cantidad: Double,
    val categoria: String,
    val descripcion: String,
    val esIngreso: Boolean
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            /*

            val context = LocalContext.current

            // Ejecutar la prueba en un hilo en segundo plano al iniciar
            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    val modelo = ModeloGastos(context)

                    // 1. Insertar categorías de prueba
                    val cat1 = modelo.insertaCategoria(Categoria(nombre = "Alimentación"))
                    val cat2 = modelo.insertaCategoria(Categoria(nombre = "Transporte"))
                    Log.d("PRUEBA_DB", "Inserción Categorías: Alimentación=$cat1, Transporte=$cat2")

                    // 2. Leer categorías para obtener sus IDs asignados por AUTOINCREMENT
                    val categorias = modelo.leeCategorias()
                    Log.d("PRUEBA_DB", "Total categorías leídas: ${categorias.size}")

                    if (categorias.isNotEmpty()) {
                        val idAlimentacion = categorias.first().id

                        // 3. Insertar un gasto vinculado
                        val gastoOk = modelo.insertaGasto(
                            Gasto(
                                categoriaId = idAlimentacion,
                                descripcion = "Comida corrida",
                                monto = 8500
                            )
                        )
                        Log.d("PRUEBA_DB", "Inserción Gasto: $gastoOk")
                    }

                    // 4. Leer el resumen agrupado con LEFT JOIN
                    val resumen = modelo.resumenCategorias()
                    resumen.forEach {
                        Log.d("PRUEBA_DB", "RESUMEN -> Categoría: ${it.nombre} | Total: \$${it.total / 100.0}")
                    }
                }
            }

            Text("Ejecutando pruebas de lectura y escritura en SQLite...")
        }


*/      AppNavegacion()


        }
    }
}


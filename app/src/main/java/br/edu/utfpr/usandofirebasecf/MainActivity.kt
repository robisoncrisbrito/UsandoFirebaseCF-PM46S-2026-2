package br.edu.utfpr.usandofirebasecf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.utfpr.usandofirebasecf.ui.theme.UsandoFirebaseCFTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsandoFirebaseCFTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    var codigo by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState() ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = "Cadastro de Aluno",
            modifier = modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = codigo,
            onValueChange = { codigo = it},
            label = { Text ("Código") },
            modifier = modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it},
            label = { Text ("Nome") },
            modifier = modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telefone,
            onValueChange = { telefone = it},
            label = { Text ("Telefone") },
            modifier = modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {},
            modifier = modifier.fillMaxWidth()
        ){
            Text( "Incluir" )
        }

        Button(
            onClick = {},
            modifier = modifier.fillMaxWidth()
        ){
            Text( "Alterar" )
        }

        Button(
            onClick = {},
            modifier = modifier.fillMaxWidth()
        ){
            Text( "Excluir" )
        }

        Button(
            onClick = {},
            modifier = modifier.fillMaxWidth()
        ){
            Text( "Pesquisar" )
        }

        Button(
            onClick = {},
            modifier = modifier.fillMaxWidth()
        ){
            Text( "Listar" )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UsandoFirebaseCFTheme {
        Greeting("Android")
    }
}
package br.edu.utfpr.usandofirebasecf

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.utfpr.usandofirebasecf.ui.theme.UsandoFirebaseCFTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

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

    val context = LocalContext.current
    val db = Firebase.firestore

    var codigo by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState() ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = "Cadastro de Aluno",
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = codigo,
            onValueChange = { codigo = it},
            label = { Text ("Código") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it},
            label = { Text ("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telefone,
            onValueChange = { telefone = it},
            label = { Text ("Telefone") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val cadastro = hashMapOf(
                    "nome" to nome,
                    "telefone" to telefone
                )

                db.collection("cadastro")
                    .document(codigo)
                    .set( cadastro )
                    .addOnSuccessListener {
                        Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT ).show()
                    }
                    .addOnFailureListener { e->
                        Toast.makeText( context, "Erro: ${e.message}", Toast.LENGTH_SHORT ).show()
                    }


            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text( "Incluir" )
        }

        Button(
            onClick = {

                val cadastro = hashMapOf(
                    "nome" to nome,
                    "telefone" to telefone
                )

                db.collection("cadastro")
                    .document(codigo)
                    .set( cadastro )
                    .addOnSuccessListener {
                        Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT ).show()
                    }
                    .addOnFailureListener { e->
                        Toast.makeText( context, "Erro: ${e.message}", Toast.LENGTH_SHORT ).show()
                    }





            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text( "Alterar" )
        }

        Button(
            onClick = {
                db.collection( "cadastro" )
                    .document(codigo)
                    .delete()
                    .addOnSuccessListener {
                        Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT ).show()
                    }
                    .addOnFailureListener { e->
                        Toast.makeText( context, "Erro: ${e.message}", Toast.LENGTH_SHORT ).show()
                    }

            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text( "Excluir" )
        }

        Button(
            onClick = {
                db.collection("cadastro")
                    .document(codigo)
                    .get()
                    .addOnSuccessListener { document ->
                        if ( document.data == null ){
                            Toast.makeText(context, "Não encontrado", Toast.LENGTH_SHORT ).show()
                        }else{
                            nome = document.data?.get("nome").toString()
                            telefone = document.data?.get("telefone") as String
                        }
                    }
                    .addOnFailureListener { e->
                        Toast.makeText( context, "Erro: ${e.message}", Toast.LENGTH_SHORT ).show()
                    }


            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text( "Pesquisar" )
        }

        Button(
            onClick = {
                var saida = StringBuilder()

                db.collection("cadastro")
                    .get()
                    .addOnSuccessListener { document ->

                        for ( registro in document ) {
                            saida.append("${registro.data.get("nome")} - ${registro.data.get("telefone")}\n")
                        }

                        Toast.makeText( context, saida.toString(), Toast.LENGTH_SHORT ).show()

                    }
                    .addOnFailureListener { e->
                        Toast.makeText( context, "Erro: ${e.message}", Toast.LENGTH_SHORT ).show()
                    }








            },
            modifier = Modifier.fillMaxWidth()
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
package com.example.vidascompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vidascompose.ui.theme.VidasComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VidasComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(94, 94, 94, 255)
                ) {
                    myApp()
                }
            }
        }
    }

    @Composable
    private fun myApp() {
        var hp1 by remember { mutableStateOf(25) }
        var hp2 by remember { mutableStateOf(25) }
        var vn1 by remember { mutableStateOf(0) }
        var vn2 by remember { mutableStateOf(0) }
        //Toda la pantalla
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            //Campo1
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                //Linea Venenos
                lineaVenenos(onAddClick = {
                    vn1+=1
                }, onRemoveClick = {
                    if(vn1>0){
                        vn1-=1
                    }
                })


                //Linea vidas y marcador
                lineaMarcador(hp1,vn1,Modifier.fillMaxWidth().weight(1f),
                    onAddClick = {hp1+=1},
                    onRemoveClick = {
                        if(hp1>0){
                            hp1-=1
                        }
                    }
                )

                //Linea transferir
                lineaTransferir(1,
                    onClick = {
                        if(hp1>0){
                            hp1-=1
                            hp2+=1
                        }
                    }
                )
            }


            //Campo2
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                //Linea transferir
                lineaTransferir(2,
                    onClick = {
                        if(hp2>0){
                            hp2-=1
                            hp1+=1
                        }
                    }
                )


                //Linea vidas y marcador
                lineaMarcador(hp2,vn2,Modifier.fillMaxWidth().weight(1f),
                    onAddClick = {hp2+=1},
                    onRemoveClick = {
                        if(hp2>0){
                            hp2-=1
                        }
                    }
                )

                //Linea Venenos
                lineaVenenos(onAddClick = {
                    vn2+=1
                }, onRemoveClick = {
                    if(vn2>0){
                        vn2-=1
                    }
                })
            }


        }
    }

    @Composable
    private fun lineaTransferir(origen:Int,onClick: () -> Unit) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            IconButton(onClick = { onClick },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(223, 223, 223, 255))
            ) {
                if(origen==1){
                    Icon(imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Transferir a jugador2",
                        modifier = Modifier.size(70.dp))
                }else{
                    Icon(imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Transferir a jugador1",
                        modifier = Modifier.size(70.dp))
                }
            }
        }
    }

    @Composable
    private fun lineaVenenos(onAddClick: () -> Unit, onRemoveClick:()->Unit) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onAddClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Veneno +", color = Color.Green)
            }
            Button(onClick = onRemoveClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Veneno -", color = Color.Green)
            }

        }
    }

    @Composable
    private fun lineaMarcador(hp: Int, vn: Int,mod:Modifier, onAddClick: () -> Unit, onRemoveClick:()->Unit) {
        Row(
            modifier = mod,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onAddClick
                ,modifier = Modifier
                    .size(70.dp)
                    .padding(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Favorite,
                    contentDescription ="Sumar Vida",
                    modifier = Modifier.size(70.dp),
                    tint = Color.Red
                )
            }
            Text(
                text = "$hp/$vn",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
            IconButton(onClick = onRemoveClick,
                modifier = Modifier
                    .size(70.dp)
                    .padding(8.dp)
            ) {
                Icon(imageVector = Icons.Default.FavoriteBorder,
                    contentDescription ="Restar Vida" ,
                    modifier = Modifier
                        .size(70.dp),
                    tint = Color.Red
                )
            }
        }
    }

    @Preview
    @Composable
    fun RowFillHeightPreview() {
        myApp()
    }
}


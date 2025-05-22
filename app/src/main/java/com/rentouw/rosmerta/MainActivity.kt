package com.rentouw.rosmerta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.rentouw.rosmerta.ui.MainViewModel // Make sure this path is correct
import com.rentouw.rosmerta.ui.theme.RosmertaTheme // Make sure this path is correct
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // For Hilt
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels() // Hilt ViewModel injection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RosmertaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BatteryScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BatteryScreen(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val batteryLogs by viewModel.batteryLogs.collectAsState()

    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            Button(onClick = { viewModel.logCurrentBatteryStatus() }) {
                Text("Log Current Battery Status")
            }
        }

        if (batteryLogs.isEmpty()) {
            item {
                Text("No battery logs yet. Click the button to log current status.")
            }
        } else {
            items(batteryLogs) { log ->
                // Consider creating a dedicated Composable for the log item for better structure
                Text("Time: ${log.timestamp}, Level: ${log.level}%, Charging: ${log.isCharging}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BatteryScreenPreview() {
    RosmertaTheme {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(Dp(16F))) {
            item {
                Button(onClick = { }) {
                    Text("Log Current Battery Status")
                }
            }
            item {
                Text("No battery logs yet. Click the button to log current status.")
            }
            this.items(listOf(
                // Dummy data for preview
                com.rentouw.lifelogging_battery.model.BatteryInfo(System.currentTimeMillis(), 80, false),
                com.rentouw.lifelogging_battery.model.BatteryInfo(System.currentTimeMillis() - 3600000, 70, true)
            )) { log ->
                Text("Time: ${log.timestamp}, Level: ${log.level}%, Charging: ${log.isCharging}")
            }
        }
    }
}

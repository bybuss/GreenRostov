package bob.colbaskin.greenrostov.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bob.colbaskin.greenrostov.data.models.Achivement
import bob.colbaskin.greenrostov.data.models.InventoryItem
import bob.colbaskin.greenrostov.data.models.Reward
import bob.colbaskin.greenrostov.data.models.TaskData
import bob.colbaskin.greenrostov.domain.network.restApi.RestApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author bybuss
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    val restApiRepository: RestApiRepository
): ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskData>>(emptyList())
    val tasks: StateFlow<List<TaskData>> = _tasks

    private val _inventory = MutableStateFlow<List<InventoryItem>>(emptyList())
    val inventory: StateFlow<List<InventoryItem>> = _inventory

    private val _achievements = MutableStateFlow<List<Achivement>>(emptyList())
    val achievements: StateFlow<List<Achivement>> = _achievements

    init {
        getTasks()
        getInventory()
        loadAchievements()
    }

    fun getTasks() {
        viewModelScope.launch{
            val list = restApiRepository.getTasks()
            _tasks.update { list }
        }
    }

    fun getInventory() {
        viewModelScope.launch{
            val inventoryList = restApiRepository.getInventoryItems()
            _inventory.update { inventoryList }
        }
    }

    fun loadAchievements() {
        viewModelScope.launch {
            val achievementList = restApiRepository.getAchievements()
            _achievements.update { achievementList }
        }
    }
}
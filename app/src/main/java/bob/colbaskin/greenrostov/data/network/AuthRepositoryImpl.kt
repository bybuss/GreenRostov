package bob.colbaskin.greenrostov.data.network

import android.util.Log
import bob.colbaskin.greenrostov.domain.network.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * @author bybuss
 */

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {

    override fun isLoggedIn(): Boolean {
        if (firebaseAuth.currentUser != null) {
            println("AuthRepository: Already logged in")
            return true
        }
        return false
    }

    override suspend fun login(email: String, password: String): Boolean {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            println("AuthRepository: Logged in")
            result.user != null
        } catch (e: Exception) {
            e.printStackTrace()
            println("AuthRepository: Login failed")
            false
        }
    }

    override suspend fun register(email: String, password: String): Boolean {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            println("AuthRepository: Signed up")
            result.user != null
        } catch (e: Exception) {
            e.printStackTrace()
            println("AuthRepository: Sign up failed")
            false
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override suspend fun getToken(): String? {
        return try {
            val user = firebaseAuth.currentUser
            user?.getIdToken(true)?.await()?.token
        } catch (e: Exception) {
            Log.e("AuthRepository", "Failed to get token", e)
            null
        }
    }
}
package com.sambas.fagiollogs.domain.navigation

import NavHost
import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.sambas.fagiollogs.core.navigation.NavGraphBuilder
import com.sambas.fagiollogs.core.navigation.composable
import com.sambas.fagiollogs.core.navigation.navigate
import com.sambas.fagiollogs.core.navigation.navigation
import com.sambas.fagiollogs.core.navigation.rememberNavControllerWithLogger
import com.sambas.fagiollogs.domain.login.LoginScreen
import com.sambas.fagiollogs.domain.login.LoginUiEvent
import com.sambas.fagiollogs.domain.login.LoginViewModel
import com.sambas.fagiollogs.domain.splash.SplashScreen
import kotlinx.coroutines.flow.collect

@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onClose: () -> Unit,
) {
    val navController = rememberNavControllerWithLogger()

    NavHost(
        navController = navController,
        graph = MainNavigationGraph,
        modifier = modifier,
    ) {
        navigation(graph = MainNavigationGraph) {
            for (destination in MainNavigationGraph.destinations) {
                when (destination) {
                    is MainNavigationGraph.SplashScreenDestination -> {
                        splashScreen(
                            destination = destination,
                            navController = navController,
                            closeCallback = onClose
                        )
                    }

                    is MainNavigationGraph.LogInScreenDestination -> {
                        loginScreen(
                            destination = destination,
                            navController = navController,
                            closeCallback = onClose
                        )
                    }
                }
            }

        }
    }
}

private fun NavGraphBuilder.splashScreen(
    destination: MainNavigationGraph.SplashScreenDestination,
    navController: NavController,
    closeCallback: () -> Unit,
) {
    composable(destination) { navBackStackEntry ->

        val viewModel = hiltViewModel<LoginViewModel>()
        val state = viewModel.state.collectAsStateWithLifecycle()
        LaunchedEffect(viewModel) {
            viewModel.events.collect { event ->
                when (event) {
                    is LoginUiEvent.UserAlreadyLoggedIn -> {
                        val loginArgs = MainNavigationGraph.LogInScreenDestination.Args(
                            isLoggedIn = false
                        )
                        navController.navigate(
                            from = destination,
                            navigationUriWithArgs = MainNavigationGraph
                                .LogInScreenDestination.navigationUri(loginArgs),
                        )
                    }

                    is LoginUiEvent.LoginError -> { /*nothing to do here*/
                    }

                    LoginUiEvent.LoginSuccess -> { /*nothing to do here*/
                    }

                    is LoginUiEvent.RegistrationError -> { /*nothing to do here*/
                    }

                    LoginUiEvent.RegistrationSuccess -> { /*nothing to do here*/
                    }

                    LoginUiEvent.UserNotLoggedIn -> {
                        val loginArgs = MainNavigationGraph.LogInScreenDestination.Args(
                            isLoggedIn = false
                        )
                        navController.navigate(
                            from = destination,
                            navigationUriWithArgs = MainNavigationGraph
                                .LogInScreenDestination.navigationUri(loginArgs),
                        )
                    }

                    is LoginUiEvent.StartGoogleSignIn -> {
                        //nothing here.
                    }
                }
            }
        }
        SplashScreen(loginUiState = state.value)

    }
}

private fun NavGraphBuilder.loginScreen(
    destination: MainNavigationGraph.LogInScreenDestination,
    navController: NavController,
    closeCallback: () -> Unit,
) {
    composable(destination) { navBackStackEntry ->
        val context = LocalContext.current
        val viewModel = hiltViewModel<LoginViewModel>()
        val state = viewModel.state.collectAsStateWithLifecycle()

        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    val credential = Identity.getSignInClient(context).getSignInCredentialFromIntent(result.data)
                    viewModel.handleGoogleSignInResult(credential)
                } catch (e: Exception) {
                    Log.d("samba2", "error: ${e.message}")
//                    viewModel.emitEvent(LoginUiEvent.LoginError("Google Sign In failed: ${e.message}"))
                }
            }
        }

        LaunchedEffect(viewModel) {
            viewModel.events.collect { event ->
                when (event) {
                    is LoginUiEvent.UserAlreadyLoggedIn -> { /*nothing to do here*/ }

                    is LoginUiEvent.LoginError -> { /*nothing to do here*/ }

                    LoginUiEvent.LoginSuccess -> { /*nothing to do here*/ }

                    is LoginUiEvent.RegistrationError -> { /*nothing to do here*/ }

                    LoginUiEvent.RegistrationSuccess -> { /*nothing to do here*/ }

                    LoginUiEvent.UserNotLoggedIn -> {
                        /*nothing to do here*/ }

                    is LoginUiEvent.StartGoogleSignIn -> {
                        val intentSenderRequest = IntentSenderRequest.Builder(event.intentSender).build()
                        launcher.launch(intentSenderRequest)
                    }
                }
            }
        }

        LoginScreen(
            loginUiState = state.value,
            onLoginClick = { userName, password ->
                viewModel.loginUser(userName, password)
            },
            onLoginGoogleClick = {
                viewModel.initiateGoogleSignIn()
            },
            onForgotPasswordClick = {},
            onCreateAccountClick = {}
        )
    }
}
package com.sambas.fagiollogs.domain.navigation

import NavHost
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.google.android.gms.auth.api.identity.Identity
import com.sambas.fagiollogs.core.navigation.NavGraphBuilder
import com.sambas.fagiollogs.core.navigation.composable
import com.sambas.fagiollogs.core.navigation.navigate
import com.sambas.fagiollogs.core.navigation.navigation
import com.sambas.fagiollogs.core.navigation.popUpTo
import com.sambas.fagiollogs.core.navigation.rememberNavControllerWithLogger
import com.sambas.fagiollogs.domain.ui.landing.LandingScreen
import com.sambas.fagiollogs.domain.ui.login.LoginScreen
import com.sambas.fagiollogs.domain.ui.login.LoginUiEvent
import com.sambas.fagiollogs.domain.ui.login.LoginViewModel
import com.sambas.fagiollogs.domain.ui.register.RegisterScreen
import com.sambas.fagiollogs.domain.ui.register.RegisterViewModel
import com.sambas.fagiollogs.domain.ui.splash.SplashScreen

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

                    is MainNavigationGraph.RegisterScreenDestination -> {
                        registerScreen(
                            destination = destination,
                            navController = navController,
                            onBackPressed = onBackPressed
                        )
                    }

                    is MainNavigationGraph.LandingScreenDestination -> {
                        landingScreen(
                            destination = destination,
                            navController = navController,
                            onBackPressed = onClose
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
                        navController.navigate(
                            from = destination,
                            navigationUri = MainNavigationGraph
                                .LandingScreenDestination.navigationUri(),
                            navOptions = navOptions {
                                popUpTo(MainNavigationGraph.SplashScreenDestination) {
                                    inclusive = true
                                }
                            }
                        )
                    }

                    is LoginUiEvent.LoginError -> { /*nothing to do here*/
                    }

                    LoginUiEvent.LoginSuccess -> {
                        navController.navigate(
                            from = destination,
                            navigationUri = MainNavigationGraph
                                .LandingScreenDestination.navigationUri(),
                            navOptions = navOptions {
                                popUpTo(MainNavigationGraph.SplashScreenDestination) {
                                    inclusive = true
                                }
                            }
                        )
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

        val launcher = rememberLauncherForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    val credential =
                        Identity.getSignInClient(context).getSignInCredentialFromIntent(result.data)
                    viewModel.handleGoogleSignInResult(credential)
                } catch (e: Exception) {
                    viewModel.emitEvent(LoginUiEvent.LoginError("Google Sign In failed: ${e.message}"))
                }
            }
        }

        LaunchedEffect(viewModel) {
            viewModel.events.collect { event ->
                when (event) {
                    is LoginUiEvent.UserAlreadyLoggedIn -> {
                        navController.navigate(
                            from = destination,
                            navigationUri = MainNavigationGraph
                                .LandingScreenDestination.navigationUri(),
                            navOptions = navOptions {
                                popUpTo(MainNavigationGraph.LogInScreenDestination) {
                                    inclusive = true
                                }
                            }
                        )
                    }

                    is LoginUiEvent.LoginError -> { /*nothing to do here*/
                    }

                    LoginUiEvent.LoginSuccess -> {
                        navController.navigate(
                            from = destination,
                            navigationUri = MainNavigationGraph
                                .LandingScreenDestination.navigationUri(),
                            navOptions = navOptions {
                                popUpTo(MainNavigationGraph.LogInScreenDestination) {
                                    inclusive = true
                                }
                            }
                        )
                    }

                    is LoginUiEvent.StartGoogleSignIn -> {
                        val intentSenderRequest =
                            IntentSenderRequest.Builder(event.intentSender).build()
                        launcher.launch(intentSenderRequest)
                    }

                    LoginUiEvent.UserNotLoggedIn -> { /*nothing to do here*/
                    }
                }
            }
        }

        LoginScreen(
            loginUiState = state.value,
            onLoginClick = { userName, password ->
                viewModel.loginUser(userName, password)
            },
            onLoginGoogleClick = viewModel::initiateGoogleSignIn,
            onForgotPasswordClick = {},
            onCreateAccountClick = {
                navController.navigate(
                    from = destination,
                    navigationUri = MainNavigationGraph
                        .RegisterScreenDestination.navigationUri(),
                )
            },
            onPasswordChange = viewModel::onPasswordChanged,
            onEmailChange = viewModel::onEmailChanged
        )
    }
}

private fun NavGraphBuilder.registerScreen(
    destination: MainNavigationGraph.RegisterScreenDestination,
    navController: NavController,
    onBackPressed: () -> Unit
) {
    composable(destination) { navBackStackEntry ->
        val viewModel = hiltViewModel<RegisterViewModel>()
        val state = viewModel.state.collectAsStateWithLifecycle()

        RegisterScreen(
            modifier = Modifier,
            registerUiState = state.value,
            onUserNameChange = viewModel::onNameChanged,
            onEmailChange = viewModel::onEmailChanged,
            onPasswordChange = viewModel::onPasswordChanged,
            onPasswordRepeatedChange = viewModel::onPasswordRepeatedChange,
            onRegisterClick = viewModel::registerUser,
            onShowPasswordText = viewModel::onShowPasswordText,
            onShowRepeatedPasswordText = viewModel::onShowRepeatedPasswordText,
            onValidateRepeatedPassword = viewModel::onValidateRepeatedPassword,
            onBackPressed = onBackPressed
        )
    }
}

private fun NavGraphBuilder.landingScreen(
    destination: MainNavigationGraph.LandingScreenDestination,
    navController: NavController,
    onBackPressed: () -> Unit
) {
    composable(destination) {
        LandingScreen()
    }
}
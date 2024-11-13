package com.sambas.fagiollogs.domain.navigation

import NavHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.sambas.fagiollogs.core.navigation.NavGraphBuilder
import com.sambas.fagiollogs.core.navigation.composable
import com.sambas.fagiollogs.core.navigation.navigate
import com.sambas.fagiollogs.core.navigation.navigation
import com.sambas.fagiollogs.core.navigation.popUpTo
import com.sambas.fagiollogs.core.navigation.rememberNavControllerWithLogger
import com.sambas.fagiollogs.domain.ui.login.LoginScreen
import com.sambas.fagiollogs.domain.ui.login.LoginUiEvent
import com.sambas.fagiollogs.domain.ui.login.LoginViewModel
import com.sambas.fagiollogs.domain.ui.register.RegisterScreen
import com.sambas.fagiollogs.domain.ui.register.RegisterUiEvent
import com.sambas.fagiollogs.domain.ui.register.RegisterViewModel
import com.sambas.fagiollogs.domain.ui.splash.SplashScreen

@Composable
internal fun AccessNavHost(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onClose: () -> Unit,
    rootNavController: NavController
) {
    val navController = rememberNavControllerWithLogger()

    NavHost(
        navController = navController,
        graph = AccessNavigationGraph,
        modifier = modifier,
    ) {
        navigation(graph = AccessNavigationGraph) {
            for (destination in AccessNavigationGraph.destinations) {
                when (destination) {
                    is AccessNavigationGraph.SplashScreenDestination -> {
                        splashScreen(
                            destination = destination,
                            navController = navController,
                            rootNavController = rootNavController,
                            closeCallback = onClose
                        )
                    }

                    is AccessNavigationGraph.LogInScreenDestination -> {
                        loginScreen(
                            destination = destination,
                            navController = navController,
                            rootNavController = rootNavController,
                            closeCallback = onClose
                        )
                    }

                    is AccessNavigationGraph.RegisterScreenDestination -> {
                        registerScreen(
                            destination = destination,
                            navController = navController,
                            rootNavController = rootNavController,
                            onBackPressed = onBackPressed
                        )
                    }
                }
            }

        }
    }
}

private fun NavGraphBuilder.splashScreen(
    destination: AccessNavigationGraph.SplashScreenDestination,
    navController: NavController,
    rootNavController: NavController,
    closeCallback: () -> Unit,
) {
    composable(destination) { navBackStackEntry ->

        val viewModel = hiltViewModel<LoginViewModel>()
        val state = viewModel.state.collectAsStateWithLifecycle()
        LaunchedEffect(viewModel) {
            viewModel.events.collect { event ->
                when (event) {
                    is LoginUiEvent.UserAlreadyLoggedIn -> {
                        rootNavController.navigate(
                            from = RootNavigationGraph.AccessGraphDestination,
                            navigationUri = RootNavigationGraph.MainGraphDestination.navigationUri(),
                            navOptions = navOptions {
                                popUpTo(RootNavigationGraph.AccessGraphDestination) {
                                    inclusive = true
                                }
                            }
                        )
                    }

                    is LoginUiEvent.LoginError -> { /*nothing to do here*/
                    }

                    LoginUiEvent.LoginSuccess -> {
                        // First save the user data in firestore
                        viewModel.saveUserToFirestore()
                        rootNavController.navigate(
                            from = RootNavigationGraph.AccessGraphDestination,
                            navigationUri = RootNavigationGraph.MainGraphDestination.navigationUri(),
                            navOptions = navOptions {
                                popUpTo(RootNavigationGraph.AccessGraphDestination) {
                                    inclusive = true
                                }
                            }
                        )
                    }

                    LoginUiEvent.UserNotLoggedIn -> {
                        val loginArgs = AccessNavigationGraph.LogInScreenDestination.Args(
                            isLoggedIn = false
                        )
                        navController.navigate(
                            from = destination,
                            navigationUriWithArgs = AccessNavigationGraph
                                .LogInScreenDestination.navigationUri(loginArgs),
                        )
                    }
                }
            }
        }
        SplashScreen(loginUiState = state.value)

    }
}

private fun NavGraphBuilder.loginScreen(
    destination: AccessNavigationGraph.LogInScreenDestination,
    navController: NavController,
    rootNavController: NavController,
    closeCallback: () -> Unit,
) {
    composable(destination) { navBackStackEntry ->
        val context = LocalContext.current
        val viewModel = hiltViewModel<LoginViewModel>()
        val state = viewModel.state.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel) {
            viewModel.events.collect { event ->
                when (event) {
                    is LoginUiEvent.UserAlreadyLoggedIn -> {
                        rootNavController.navigate(
                            from = RootNavigationGraph.AccessGraphDestination,
                            navigationUri = RootNavigationGraph.MainGraphDestination.navigationUri(),
                            navOptions = navOptions {
                                popUpTo(RootNavigationGraph.AccessGraphDestination) {
                                    inclusive = true
                                }
                            }
                        )
                    }

                    is LoginUiEvent.LoginError -> { /*nothing to do here*/
                    }

                    LoginUiEvent.LoginSuccess -> {
                        // First save the user data in firestore
                        viewModel.saveUserToFirestore()
                        rootNavController.navigate(
                            from = RootNavigationGraph.AccessGraphDestination,
                            navigationUri = RootNavigationGraph.MainGraphDestination.navigationUri(),
                            navOptions = navOptions {
                                popUpTo(RootNavigationGraph.AccessGraphDestination) {
                                    inclusive = true
                                }
                            }
                        )
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
            onForgotPasswordClick = viewModel::onPasswordResetRequested,
            onCreateAccountClick = {
                navController.navigate(
                    from = destination,
                    navigationUri = AccessNavigationGraph
                        .RegisterScreenDestination.navigationUri(),
                )
            },
            onPasswordChange = viewModel::onPasswordChanged,
            onEmailChange = viewModel::onEmailChanged
        )
    }
}

private fun NavGraphBuilder.registerScreen(
    destination: AccessNavigationGraph.RegisterScreenDestination,
    navController: NavController,
    rootNavController: NavController,
    onBackPressed: () -> Unit
) {
    composable(destination) { navBackStackEntry ->
        val viewModel = hiltViewModel<RegisterViewModel>()
        val state = viewModel.state.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel) {
            viewModel.events.collect { event ->
                when (event) {
                    RegisterUiEvent.RegistrationSuccess -> {
                        viewModel.saveNewUserToFirestore()
                        rootNavController.navigate(
                            from = RootNavigationGraph.AccessGraphDestination,
                            navigationUri = RootNavigationGraph.MainGraphDestination.navigationUri(),
                            navOptions = navOptions {
                                popUpTo(RootNavigationGraph.AccessGraphDestination) {
                                    inclusive = true
                                }
                            }
                        )
                    }
                }
            }
        }

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


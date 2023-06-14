package com.example.instagramclone.di

import com.example.instagramclone.data.AuthenticationRepositoryImpl
import com.example.instagramclone.data.PostRepositoryImpl
import com.example.instagramclone.data.UserRepositoryImpl
import com.example.instagramclone.domain.repository.AuthRepository
import com.example.instagramclone.domain.repository.PostRepository
import com.example.instagramclone.domain.repository.UserRepository
import com.example.instagramclone.domain.use_cases.*
import com.example.instagramclone.domain.use_cases.authenticationusecase.*
import com.example.instagramclone.domain.use_cases.post_usecases.GetAllPost
import com.example.instagramclone.domain.use_cases.post_usecases.PostUseCases
import com.example.instagramclone.domain.use_cases.post_usecases.UploadPost
import com.example.instagramclone.domain.use_cases.user_usecase.GetUserDetails
import com.example.instagramclone.domain.use_cases.user_usecase.SetUserDetails
import com.example.instagramclone.domain.use_cases.user_usecase.UserUseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InstagramCloneModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthentication(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage():FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(auth: FirebaseAuth,firestore: FirebaseFirestore): AuthRepository{
        return AuthenticationRepositoryImpl(auth = auth, firestore = firestore)
    }
    @Singleton
    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository = repository),
       firebaseAuthState = FirebaseAuthState(repository = repository),
       firebaseSignOut = FirebaseSignOut(repository = repository),
       firebaseSignIn = FirebaseSignIn(repository = repository),
       firebaseSignUp = FirebaseSignUp(repository = repository)
    )

    @Singleton
    @Provides
    fun provideUserRepository(firebaseFirestore: FirebaseFirestore) : UserRepository{
        return UserRepositoryImpl(firebaseFirestore = firebaseFirestore)
    }

    @Singleton
    @Provides
    fun providesUserUseCases(repository: UserRepository) = UserUseCases(
        getUserDetails = GetUserDetails(repository = repository),
        setUserDetails = SetUserDetails(repository = repository)
    )

    @Singleton
    @Provides
    fun providePostRepository(firebaseFirestore: FirebaseFirestore): PostRepository {
        return PostRepositoryImpl(firebaseFirestore = firebaseFirestore)
    }

    @Singleton
    @Provides
    fun providePostsUseCases(repository: PostRepository) = PostUseCases(
        getAllPost = GetAllPost(repository = repository),
        uploadPost = UploadPost(repository = repository)
    )

}
package com.jasiri.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.jasiri.data.sources.local.serializers.UserTokenSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import com.jasiri.data.utils.DataConstants.STORE_NAME
import javax.inject.Singleton
import com.jasiri.data.UserToken

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun providesDataStorePreferences(
        @ApplicationContext appContext: Context,
        @AppCoroutineScope appCoroutineScope: CoroutineScope
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            scope = appCoroutineScope,
            produceFile = { appContext.preferencesDataStoreFile(STORE_NAME) }
        )
    }

    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        @AppCoroutineScope appCoroutineScope: CoroutineScope,
        userTokenSerializer: UserTokenSerializer
    ): DataStore<UserToken> = DataStoreFactory.create(
        serializer = userTokenSerializer,
        scope = appCoroutineScope
    ) {
        context.dataStoreFile("jasiri_user_preferences.pb")
    }
}
package com.imran.currencyapp.helper

import org.mockito.ArgumentCaptor
import org.mockito.Mockito

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */


inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
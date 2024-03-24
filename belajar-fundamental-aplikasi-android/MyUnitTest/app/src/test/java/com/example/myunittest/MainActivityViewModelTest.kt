package com.example.myunittest

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class MainActivityViewModelTest {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var cuboidModel: CuboidModel

    private val dummyLength = 12.0
    private val dummyWidth = 7.0
    private val dummyHeight = 6.0

    private val dummyVolume = 504.0
    private val dummyCircumference = 100.0
    private val dummySurfaceArea = 396.0

    @Before
    fun before(){
        cuboidModel = mock(CuboidModel::class.java)
        mainActivityViewModel = MainActivityViewModel(cuboidModel)
    }

    @Test
    fun testVolume() {
        cuboidModel = CuboidModel()
        mainActivityViewModel = MainActivityViewModel(cuboidModel)
        mainActivityViewModel.save(dummyWidth, dummyLength, dummyHeight)
        val volume = mainActivityViewModel.getVolume()
        assertEquals(dummyVolume, volume, 0.0001)
    }

    @Test
    fun getCircumference() {
        cuboidModel = CuboidModel()
        mainActivityViewModel = MainActivityViewModel(cuboidModel)
        mainActivityViewModel.save(dummyWidth, dummyLength, dummyHeight)
        val circumference = mainActivityViewModel.getCircumference()
        assertEquals(dummyCircumference, circumference, 0.00001)
    }

    @Test
    fun getSurfaceArea() {
        cuboidModel = CuboidModel()
        mainActivityViewModel = MainActivityViewModel(cuboidModel)
        mainActivityViewModel.save(dummyWidth, dummyLength, dummyHeight)
        val surfaceArea = mainActivityViewModel.getSurfaceArea()
        assertEquals(dummySurfaceArea, surfaceArea, 0.0001)
    }

    @Test
    fun getVolume() {
    }

    @Test
    fun save() {
    }

    @Test
    fun testMockVolume(){
        `when`(mainActivityViewModel.getVolume()).thenReturn(dummyVolume)
        val volume = mainActivityViewModel.getVolume()
        verify(cuboidModel).getVolume()
        assertEquals(dummyVolume, volume, 0.0001)
    }

    @Test
    fun testMockCircumference() {
        `when`(mainActivityViewModel.getCircumference()).thenReturn(dummyCircumference)
        val circumference = mainActivityViewModel.getCircumference()
        verify(cuboidModel).getCircumference()
        assertEquals(dummyCircumference, circumference, 0.0001)
    }
    @Test
    fun testMockSurfaceArea() {
        `when`(mainActivityViewModel.getSurfaceArea()).thenReturn(dummySurfaceArea)
        val surfaceArea = mainActivityViewModel.getSurfaceArea()
        verify(cuboidModel).getSurfaceArea()
        assertEquals(dummySurfaceArea, surfaceArea, 0.0001)
    }
}
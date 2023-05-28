package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavePatientViewModel: ViewModel() {

    private val _email = MutableLiveData<String>()

    private val _name = MutableLiveData<String>()

    private val _wpp = MutableLiveData<String>()

    private val _cpf = MutableLiveData<String>()

    private val _gender = MutableLiveData<String>()

    private val _date = MutableLiveData<String>()

    private val _profission = MutableLiveData<String>()

    private val _schooling = MutableLiveData<String>()

    private val _country = MutableLiveData<String>()

    private val _value = MutableLiveData<String>()

    private val _observation = MutableLiveData<String>()

    private val _address = MutableLiveData<String>()

    private val _number = MutableLiveData<String>()

    private val _neighborhood = MutableLiveData<String>()

    private val _city= MutableLiveData<String>()

    private val _uf = MutableLiveData<String>()

    private val _contactName = MutableLiveData<String>()

    private val _contactWpp= MutableLiveData<String>()

    private val _patientId = MutableLiveData<String>()


    fun nameValue(name: String) {
        _name.value = name
    }

    fun emailValue(email: String) {
        _email.value = email
    }

    fun wppValue(phone: String) {
        _wpp.value = phone
    }

    fun cpfValue(link : String){
        _cpf.value = link
    }

    fun genderValue(cpf: String) {
        _gender.value = cpf
    }

    fun dateValue(pass: String) {
        _date.value = pass
    }

    fun professionValue(senha: String) {
        _profission.value = senha
    }

    fun schoolingValue(senha: String) {
        _schooling.value = senha
    }

    fun countryValue(senha: String) {
        _country.value = senha
    }

    fun valueValue(senha: String) {
        _value.value = senha
    }

    fun observationValue(senha: String) {
        _observation.value = senha
    }

    fun addressValue(senha: String) {
        _address.value = senha
    }

    fun numberValue(senha: String) {
        _number.value = senha
    }

    fun neighborhoodValue(senha: String) {
        _neighborhood.value = senha
    }

    fun cityValue(senha: String) {
        _city.value = senha
    }

    fun ufValue(senha: String) {
        _uf.value = senha
    }

    fun contactNameValue(senha: String) {
        _contactName.value = senha
    }

    fun contactWppValue(senha: String) {
        _contactWpp.value = senha
    }

    fun patientIdValue(senha: String) {
        _patientId.value = senha
    }

}
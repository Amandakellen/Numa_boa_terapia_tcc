package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.data.repository.FirebaseResponseRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class SavePatientViewModel: ViewModel() {
    private var firebaseRepository = FirebaseResponseRepository()

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

    private fun hashMapData(): HashMap<String, String> {
        return hashMapOf(
            "patient_name" to _name.value.toString(),
            "patient_email" to _email.value.toString(),
            "patient_wpp" to _wpp.value.toString(),
            "patient_cpf" to _cpf.value.toString(),
            "patient_gender" to _gender.value.toString(),
            "patient_birthday" to _date.value.toString(),
            "patient_profession" to _profission.value.toString(),
            "patient_schooling" to _schooling.value.toString(),
            "patient_country" to _country.value.toString(),
            "patient_payment" to _value.value.toString(),
            "patient_observation" to _observation.value.toString(),
            "patient_address" to _address.value.toString(),
            "patient_number" to _number.value.toString(),
            "patient_neighborhood" to _neighborhood.value.toString(),
            "patient_city" to _city.value.toString(),
            "patient_uf" to _uf.value.toString(),
            "patient_contact_name" to _contactName.value.toString(),
            "patient_contact_wpp" to _contactWpp.value.toString(),
            "patient_uid" to _patientId.value.toString()


        )

    }

    fun saveValue(): Deferred<String> {

        val result = viewModelScope.async {
            firebaseRepository.saveData(
                "psi_patient_list",
                hashMapData()
            )
        }
        return result
    }

    suspend fun verifyIfIsPacient():Boolean{
        var result =viewModelScope.async{
            firebaseRepository.checkValueInPacientList(_patientId.value.toString())
        }

        var retorno = false
        result.await().collect{
            retorno = it== true
        }
        return retorno
    }
}
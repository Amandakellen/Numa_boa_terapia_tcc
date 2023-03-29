package com.example.numaboaterapia.register.psychologist.data

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.example.numaboaterapia.R

enum class PsiSpecialtiesEnum(@StringRes val specialtiesName: Int, @IdRes val id: Int) {
    GRAVIDEZ(R.string.gravidez, R.id.gravidez_check_box),
    ADOCAO(R.string.adocao, R.id.adocao_checkbox),
    ANSIEDADE(R.string.ansiedade, R.id.ansiedade_checkbox),
    APRENDIZAGEM(R.string.aprendizagem, R.id.aprendizagem_checkbox),
    CASAL(R.string.casal, R.id.casal_checkbox),
    CASAMENTO(R.string.casamento, R.id.casamento_checkbox),
    BARIATRICA(R.string.bariatrica, R.id.bariatrica_checkbox),
    COMPULSOES(R.string.compulsao, R.id.compulsoes_checkbox),
    CONFLITOS_AMOROSOS(R.string.conflitos_amorosos, R.id.conflitos_amorosos_checkbox),
    CONFLITOS_COM_A_LEI(R.string.conflitos_lei, R.id.conflitos_lei_checkbox),
    CONFLITOS_FAMILIARES(R.string.conflitos_familia, R.id.conflitos_familiares_checkbox),
    DEPENDENCIA_QUIMICA(R.string.dependencia_quimica, R.id.dependencia_quimica_checkbox),
    DESENVOLVIMENTO_COMP_PROFISSIONAIS(R.string.competencia_profissional, R.id.dev_comp_checkbox),
    DESENVOLVIMENTO_PESSOAL(R.string.desenvolvimento_pessoal, R.id.dev_pessoal_checkbox),
    DOR(R.string.dor, R.id.dor_checkbox),
    EDUCACAO_EM_DIABETES(R.string.diabetes, R.id.diabetes_checkbox),
    EMAGRECIMENTO(R.string.emagrecimento, R.id.emagrecimento_checkbox),
    ENTREVISTAS_PSICOLOGICAS(R.string.entrevistas, R.id.entrevistas_checkbox),
    ESTRESSE(R.string.estresse, R.id.estresse_checkbox),
    ESTRESSE_POS_TRAUMATICO(R.string.pos_trauma, R.id.pos_trauma_checkbox),
    FAMILIA(R.string.familia, R.id.familia_checkbox),
    FOBIA_SOCIAL(R.string.fobia_social, R.id.fobia_social_checkbox),
    FOBIAS(R.string.fobias, R.id.fobias_checkbox),
    IDOSO(R.string.idoso, R.id.idoso_checkbox),
    INCERTEZAS_FUTURO(R.string.incertezas, R.id.incertezas_checkbox),
    MATERNIDADE(R.string.maternidade, R.id.maternidade_checkbox),
    MEDOS_FOBIAS(R.string.medos, R.id.medos_checkbox),
    MORTE_LUTO(R.string.morte, R.id.morte_checkbox),
    OBESIDADE(R.string.obesidade, R.id.obesidade_checkbox),
    ORIENTACAO_DE_PAIS(R.string.pais, R.id.pais_checkbox),
    ORIENTACAO_ARENDIZAGEM(R.string.aprendizagem_problema, R.id.aprendizagem_problema_checkbox),
    ORIENTACAO_PROFISSIONAL(
        R.string.orientacao_profissional,
        R.id.orientacao_profissional_checkbox
    ),
    ORIENTACAO_PSICOLOGICA(R.string.orientacao_psicologica, R.id.orientacao_psicologica_checkbox),
    ORIENTACAO_PSICOPEDAGOGICA(R.string.Opsicopedagogica, R.id.Opsicopedagogica_checkbox),
    ENFERMOS(R.string.enfermos, R.id.enfermos_checkbox),
    PARENTALIDADE(R.string.parentalidade, R.id.parentalidade_checkbox),
    PATERNIDADE(R.string.paternidade, R.id.paternidade_checkbox),
    PCD(R.string.pcd, R.id.pcd_checkbox),
    PLANEJAMENTO_PSICOPEDAGOGICO(R.string.planejamento_psico, R.id.planejamento_psico_checkbox),
    PREPARACAO_APOSENTADORIA(R.string.aposentadoria, R.id.aposentadoria_checkbox),
    PSICOLOGIA_PERINATAL(R.string.perinatal, R.id.perinatal_checkbox),
    PUERPERIO(R.string.puerperio, R.id.puerperio_checkbox),
    QUESTOES_RACIAIS(R.string.racial, R.id.racial_checkbox),
    REABILITACAO_COGNITIVA(R.string.cognitiva, R.id.cognitiva_checkbox),
    REABILITACAO_NEUROPSICOLOGICA(R.string.neuro, R.id.neuro_checkbox),
    REALIZACAO_DE_EXAMES_PSICOLOGICOS(R.string.exames, R.id.exames_checkbox),
    RELACIONAMENTOS_AFETIVOS(R.string.relacionamentos, R.id.relacionamentos_checkbox),
    SAUDE_MENTAL(R.string.saude_mental, R.id.saude_mental_checkbox),
    SELECAO_DE_PESSOAS_POR_COMPETENCIA(R.string.selecao, R.id.selecao_checkbox),
    SEXUALIDADE_E_IDENTIDADE_DE_GENERO(R.string.sexualidade, R.id.sexualidade_checkbox),
    SINDROME_DO_PANICO(R.string.panico, R.id.panico_checkbox),
    SUICIDIO(R.string.suicidio, R.id.suicidio_checkbox),
    TANATOLOGIA(R.string.tanatologia, R.id.tanatologia_checkbox),
    TDAH(R.string.tdah, R.id.tdah_checkbox),
    TESTES_PSICOLOGICOS(R.string.testes, R.id.testes_checkbox),
    TOC(R.string.toc, R.id.toc_checkbox),
    TRANSTORNO_BIPOLAR(R.string.bipolar, R.id.bipolar_checkbox),
    TRANSTORNO_DE_HUMOR(R.string.humor, R.id.humor_checkbox),
    TRANSTORNOS_ALIMENTARES(R.string.alimentares, R.id.alimentares_checkbox),
    TRANSTORNO_DO_SONO(R.string.sono, R.id.sono_checkbox),
    TREINAMENTO_DE_MEMORIA(R.string.memoria, R.id.memoria_checkbox),
    VIOLENCIA_DOMESTICA(R.string.domestica, R.id.domestica_checkbox),
    VIOLENCIA_SEXUAL(R.string.sexual, R.id.sexual_checkbox)
}
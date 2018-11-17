/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    $("#divCalendario").hide();
});

PlanCapacitacionJS = {  

    seleccionarTipoPlan: function(valor) {
        console.warn("123 -> " + valor);
        if(valor === '7') {
            PlanCapacitacionJS.ocultarTipoPlanInterna();
        } else {
            PlanCapacitacionJS.mostrarTipoPlanExterna();
        }
    },
    
    seleccionarTipoPlan1: function(valor) {
        console.warn("123 -> " + valor);
        if(valor === '7') {
            PlanCapacitacionJS.ocultarTipoPlanInterna1();
        } else {
            PlanCapacitacionJS.mostrarTipoPlanExterna1();
        }
    },
    
    seleccionarTipoRecurso: function(valor) {
        
        if(valor === '30') {//material escritorio
            $("#recursoMaterial").show();
            $("#recursoInformatico").hide();
        } else {
            $("#recursoMaterial").hide();
            $("#recursoInformatico").show();
        }
    }, 

    ocultarTipoPlanInterna: function() { 
        $("#frmListadoPlanCapacitacion\\:lblPeriodo").show();
        $("#frmListadoPlanCapacitacion\\:cmbPeriodo").show();
        $("#frmListadoPlanCapacitacion\\:lblFechas").hide();
        $("#frmListadoPlanCapacitacion\\:clrFechaDesde_input").hide();
        $("#frmListadoPlanCapacitacion\\:clrFechaHasta_input").hide();
        $("#divCalendario").hide();
    },
    
    mostrarTipoPlanExterna: function() {
        $("#frmListadoPlanCapacitacion\\:lblPeriodo").hide();
        $("#frmListadoPlanCapacitacion\\:cmbPeriodo").hide();
        $("#frmListadoPlanCapacitacion\\:lblFechas").show();
        $("#frmListadoPlanCapacitacion\\:clrFechaDesde_input").show();
        $("#frmListadoPlanCapacitacion\\:clrFechaHasta_input").show();
        $("#divCalendario").show();
    },
    
    ocultarTipoPlanInterna1: function() { 
        $("#frmListSolicitudCapacitacion\\:lblPeriodo").show();
        $("#frmListSolicitudCapacitacion\\:cmbPeriodo").show();
        $("#frmListSolicitudCapacitacion\\:lblFechas").hide();
        $("#frmListSolicitudCapacitacion\\:clrFechaDesde_input").hide();
        $("#frmListSolicitudCapacitacion\\:clrFechaHasta_input").hide();
        $("#divCalendario").hide();
    },
    
    mostrarTipoPlanExterna1: function() {
        $("#frmListSolicitudCapacitacion\\:lblPeriodo").hide();
        $("#frmListSolicitudCapacitacion\\:cmbPeriodo").hide();
        $("#frmListSolicitudCapacitacion\\:lblFechas").show();
        $("#frmListSolicitudCapacitacion\\:clrFechaDesde_input").show();
        $("#frmListSolicitudCapacitacion\\:clrFechaHasta_input").show();
        $("#divCalendario").show();
    }
  
    
};
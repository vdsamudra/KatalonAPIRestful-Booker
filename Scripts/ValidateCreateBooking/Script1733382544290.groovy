import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper as JsonSlurper
import groovy.json.JsonOutput as JsonOutput

// Buat nama user radom yang melakukan booking
GlobalVariable.firstname =  "firstname" + new Random().nextInt(1000)
GlobalVariable.lastname =  "firstname" + new Random().nextInt(1000)

response = WS.sendRequest(findTestObject('CreateBooking'))

WS.verifyResponseStatusCode(response, 200)

// Parse response body ke JSON
def responseBody = response.getResponseText()

// Tampilkan respons dari CreateBooking
println('CreateBooking Response Body:\n' + responseBody)

def jsonResponse = new JsonSlurper().parseText(responseBody)

// Verifikasi apakah properti "bookingid" ada dan tidak null atau kosong
assert jsonResponse.bookingid.toString().trim() != '' : 'Properti \'bookingid\' kosong'

// Simpan bookingid ke dalam variabel (sebagai global atau environment variable)
def bookingId = jsonResponse.bookingid

// Menyimpan bookingId dalam environment variable
GlobalVariable.bookingid = bookingId

println('Booking ID: ' + GlobalVariable.bookingid)

//GlobalVariable.firstname = firstName

println('Fisrt Name: ' + GlobalVariable.firstname)

//GlobalVariable.lastname = lastName

println('Last Name ' + GlobalVariable.lastname)

response2 = WS.sendRequest(findTestObject('GetBookingIds'))

WS.verifyResponseStatusCode(response2, 200)

// Parse dan tampilkan respons dari GetBookingIds
def responseBody2 = response2.getResponseText()

println('Raw Response Body from GetBookingIds:\n' + responseBody2)

def jsonResponse2 = new JsonSlurper().parseText(responseBody2)

// Pretty print JSON
println('Pretty Printed JSON Response:\n' + JsonOutput.prettyPrint(JsonOutput.toJson(jsonResponse2)))

response3 = WS.sendRequest(findTestObject('GetBooking'))

WS.verifyResponseStatusCode(response3, 200)

// Parse dan tampilkan respons dari GetBooking
def responseBody3 = response3.getResponseText()

println('Raw Response Body from GetBooking:\n' + responseBody3)

def jsonResponse3 = new JsonSlurper().parseText(responseBody3)

// Pretty print JSON
println('Pretty Printed JSON Response:\n' + JsonOutput.prettyPrint(JsonOutput.toJson(jsonResponse3)))
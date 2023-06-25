package com.example.licenta.services;

import com.example.licenta.ActiveLoanDetailsActivity;
import com.example.licenta.dto.LoanRequest;
import com.example.licenta.utils.CreditType;

public class MailService {

    public static String setTextForApprovedCreditLoan(LoanRequest loanRequest) {
        StringBuilder text = new StringBuilder();
        text.append("Your loan request has been approved by our system!").append("\n").append("You will receive a money transfer to the account you provided in 24 to 48 hours. That money can be used however you feel fit. ");
        text.append("\n");
        text.append("Be careful to pay every month before the 15th, or else there will be penalties!");
        text.append("\n").append("The payment needs to be made via transfer to the following account: DGBNK1234566789.").append("\n");
        text.append("This is the information you provided in the request form. Please make sure everything is okay. If not, please contact us to make changes.");
        setPersonalInfo(loanRequest, text);
        text.append("\n").append("If you need any more information, please don't hesitate to contact us.");
        text.append("\n").append("\n");
        text.append("Happy that you are our client!").append("\n").append("\n");
        text.append("Best regards,").append("\n").append("\n").append("Your team at DigitalBank");
        return text.toString();
    }

    public static void setPersonalInfo(LoanRequest loanRequest, StringBuilder text) {
        text.append("\n").append("Credit Type: ").append(loanRequest.getCreditType());
        text.append("\n").append("Total Amount: ").append(loanRequest.getTotalAmount());
        text.append("\n").append("Credit Period: ").append(loanRequest.getPeriod()).append(" months");
        text.append("\n").append("Interest Rate: ").append(loanRequest.getInterestRate());
        text.append("\n").append("IBAN: ").append(loanRequest.getIBAN());
        text.append("\n").append("Number of kids: ").append(loanRequest.getNrKids());
        text.append("\n").append("Family situation: ").append(loanRequest.getFamilySituation());
        text.append("\n").append("Occupation: ").append(loanRequest.getOccupation());
        text.append("\n").append("Date of employment: ").append(loanRequest.getDateOfEmployment());
        text.append("\n").append("Monthly income: ").append(loanRequest.getMonthlyIncome());
        int monthlyRate = (int) ActiveLoanDetailsActivity.getMonthlyInterestEffectively(loanRequest.getTotalAmount(), loanRequest.getPeriod(), loanRequest.getInterestRate() / 10);
        text.append("\n").append("Calculated monthly rate: ").append(monthlyRate);
    }

    public static String setTextForEmailQuizResults(CreditType creditType) {
        StringBuilder text = new StringBuilder();
        text.append("Here are your quiz results!").append("\n").append("After carefully analysing your answers, we think this is the best solution for you: ").append(creditType.toString()).append(".");
        text.append("\n");
        if (creditType.equals(CreditType.STUDENT_LOAN)) {
            setTextForStudent(text);
        }
        if (creditType.equals(CreditType.TRAVEL_LOAN)) {
            setTextForTravel(text);
        }
        if (creditType.equals(CreditType.PERSONAL_LOAN)) {
            setTextForPersonal(text);
        }
        if (creditType.equals(CreditType.HOUSE_LOAN)) {
            setTextForHouse(text);
        }
        text.append("If you need any more information, please don't hesitate to contact us.");
        text.append("\n").append("\n");
        text.append("Happy that you are our client!").append("\n").append("\n");
        text.append("Best regards,").append("\n").append("\n").append("Your team at DigitalBank");
        return text.toString();
    }

    private static void setTextForHouse(StringBuilder text) {
        text.append("Since you need a larger amount, we think the house loan is the best for you. This loan was designed for big investments, mainly in one thing.");
        text.append("\n").append("\n");
        text.append("Here are some interesting articles we curated specifically for you!").append("\n");
        text.append("https://blog.deltastudio.ro/planificare-buget-cat-costa-o-amenajare-premium-proiecte-finalizate/").append("\n");
        text.append("https://mobexpert.ro/blogs/ghidul-cumparatorului/costuri-amenajari-interioare").append("\n");
        text.append("https://www.zf.ro/burse-fonduri-mutuale/profil-de-investitor-cum-a-reusit-un-economist-din-bucuresti-sa-21306149").append("\n");
    }

    private static void setTextForPersonal(StringBuilder text) {
        text.append("Since you like to spend money on luxurious items, we think the personal loan is the best for you. This loan was designed for everyday pleasures, not only in one investment.");
        text.append("\n").append("\n");
        text.append("Here are some interesting articles we curated specifically for you!").append("\n");
        text.append("https://madisonperfumery.com/?lang=ro").append("\n");
        text.append("https://casafrumoasa.ro/?gclid=Cj0KCQjwy9-kBhCHARIsAHpBjHhj_Pdqva-2MM2-nPxVjxRRPLB5aHw5_dgTJLXwLfW21rloYp6ignoaAqRJEALw_wcB").append("\n");
        text.append("https://ziare.com/investitie/top-3-categorii-de-bunuri-de-lux-ce-reprezinta-o-investitie-inteligenta-1779445").append("\n");
    }

    private static void setTextForTravel(StringBuilder text) {
        text.append("Since you enjoy travelling and do not require a big amount of money, we think the travel loan is the best for you. This loan was designed for personal pleasures, especially for people that like to create new experiences.");
        text.append("\n").append("\n");
        text.append("Here are some interesting articles we curated specifically for you!").append("\n");
        text.append("https://aventurescu.ro/top-10-destinatii-exotice-si-accesibile-pentru-o-luna-de-miere-de-neuitat/").append("\n");
        text.append("https://www.euronews.ro/articole/raport-in-ce-zile-si-la-ce-ore-pot-cumpara-romanii-cele-mai-ieftine-bilete-de-avi").append("\n");
        text.append("https://www.momondo.ro/discover/cele-mai-frumoase-destinatii-planeta-pamant").append("\n");
    }

    private static void setTextForStudent(StringBuilder text) {
        text.append("Since you are a student right now, we think the student loan will be your new best friend. This loan was designed for young people still in school, in need of money till graduating.");
        text.append("\n").append("\n");
        text.append("Here are some interesting articles we curated specifically for you!").append("\n");
        text.append("https://www.scoaladebani.ro/blog/cum-sa-iti-organizezi-bugetul-pe-perioada-studentiei").append("\n");
        text.append("https://www.uopeople.edu/blog/10-must-watch-ted-talks-for-students/").append("\n");
        text.append("https://summer.harvard.edu/blog/8-time-management-tips-for-students/").append("\n");
    }
}

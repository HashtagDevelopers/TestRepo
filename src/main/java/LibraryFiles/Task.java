package LibraryFiles;

import java.util.ArrayList;
import java.util.Arrays;

public class Task {
	public static ArrayList<String> getActTaskList(String Department) {
		ArrayList<String> actTaskList = new ArrayList<String>();

		switch (Department) {

		case "Maintenance":
			actTaskList.addAll(Arrays.asList("maintenance welfare chaser", "window restrictor database",
					"maintenance database close (including db update, email to hotel and crh)",
					"offline rooms database", "offline room (sbhl support email)", "rms update comments",
					"rms room status change", "pending crh defects update", "rms ticket approve (including email)",
					"new crh defects (including db update, email to hotel and crh)", "crh ticket update in rms",
					"email to landlord", "ir & further updates (including log)", "window restrictor chaser",
					"ir maintenace report", "weekly window restrictor report", "weekly report maintenance",
					"monthly report maintenance", "calls", "emails", "meeting", "meeting minutes", "training",
					"other tasks"));
			break;
		case "Care Support":
			actTaskList
					.addAll(Arrays.asList("charity database", "welfare database", "incident reports", "rms upload", "poster making",
							"ppt update", "weekly report - care support", "monthly report - care support",
							"calls", "emails", "meeting", "meeting minutes", "training", "other tasks"));
			break;
		case "IT":
			actTaskList.addAll(Arrays.asList("daily top up vouchers", "rms amendments minor",
					"scalefusion(depends on issue) (per operation)", "rms amendments major",
					"weekly it portal amendment reports", "oia printing papers report(weekly)",
					"weekly scalefusion report", "adams code report(weekly)", "lls weekly report",
					"rms testing as per sufiyan task", "log sheet update",
					"admin center (emails distribution center and new email creation)",
					"device inventory weekly report", "weekly top-up report", "weekly purchase inventory report",
					"monthly it portal amendments report", "monthly oia printing papers report", "lls month report",
					"monthly top-up voucher report", "weekly report for offline tablets and information screens",
					"monthly it procurement purchase inventory report", "quick assist", "weekly equals report",
					"monthly adams code report", "monthly it equals report", "under diagnosis report weekly",
					"data verification & confirmation", "online sheet access check", "rms amendments on james's advise",
					"license report", "calls", "emails", "meeting", "meeting minutes", "training", "other tasks"));
			break;
		case "Food":
			actTaskList.addAll(Arrays.asList("hot food order", "2 meal option order", "hot food order (self catering)",
					"extra meal order with( log, email to site & supplier)", "order check - food", "delivery note - food",
					"dry stock or perishable order - (allan reeder)", "dry stock or perishable order - (7 packaging)",
					"freshways including dairy", "perishable order - brakes", "dry stock order - brakes",
					"dry stock - (dudra & dt)", "perishable order - (dudra & dt)", "order corrected (perishables)",
					"dudra oranges order", "online cutlery order -pipack", "dairy orders brakes",
					"provider sheet update - food", "face mask order", "menu comms per caterer",
					"food irs / missing / shortage (including email reply , email to supplier and database log)",
					"food pending ir update - (including email reply , email to supplier and database log)",
					"food pending ir close", "daily fulfilment entries check", "daily fulfilment report",
					"serco sites snacks order with email", "food hygiene ratings", "savings log entry",
					"food picture check per day", "volume sheet - dt", "volume sheet - brakes", "volume sheet - dudra",
					"volume sheet - 7p", "volume sheet - freshways", "volume sheet - allan reeder",
					"volume sheet - damas gate (cultural snacks)", "volume sheet - baby provision",
					"dry stock order corrected", "cultural snacks", "dry stock invoices log", "gp log", "guest meeting",
					"trial data log", "crh / serious food concerns",
					"trial project allocation sent to caterers", "new online forms (from existing template)",
					"dry stock form price update - per form", "dry stock form update - per form", "compliance data log",
					"comms", "decanting site stock transfer sheet update", "council follow up for fbr",
					"online form update with email confirmation", "hotel setup log", "decant log",
					"food wastage report (excel caterer wise & send)",
					"weekly or monthly hot food & dry stock report with key points", "food survey report weekly",
					"weekly savings report", "monthly savings report excel (per week)", "monthly savings report ppt",
					"food survey report - caterer wise - (create)", "food survey report - caterer wise - (send)",
					"food picture report", "fbr (simple)", "fbr (complicated)", "menu review per week",
					"monthly breads & fruits expiry report", "food confirmation report",
					"consolidated food wastage report", "department monthly report review", "todler food survey report",
					"food trial data summary report per site", "serco sites snacks delivery & invoice check",
					"email scanning - food", "extra hot food order sheet (weekly)",
					"9 cuisine room number sheet update", "9 cuisine room number comms", "welfare check updates",
					"special meal sheet check (crown plaza / maidenhead)", "brown bag order",
					"site allocation/decant emails", "calls", "emails", "meeting", "meeting minutes", "handover",
					"training", "other tasks"));
			break;
		case "Data Analyst":
			actTaskList.addAll(Arrays.asList("reports update", "reports chaser", "lr data compilation (per sheet)",
					"weekly / monthly reports checking (per dept.:food, procurement, laundry, security)",
					"data sheet cleaning", "department ppt access to depts", "lr monthly sheet merge",
					"discrepancy note (per entry)", "delivery note link attach", "delivery notes checking",
					"delivery notes reminder", "delivery email scaning (per thread)",
					"hot food report data compilation (per caterer)", "power bi weekly report (total 9)",
					"power bi weekly report - checking", "power bi weekly dashbord",
					"hot food report making - self chef", "hot food report making - caterer",
					"su demographic data cleaning", "hot food delivery confirmation report",
					"food wastage report (excel caterer wise)", "food survey report weekly data analyst", "food picture report data analyst",
					"volume sheet - dt", "volume sheet - brakes", "volume sheet - dudra", "volume sheet - 7p",
					"volume sheet - freshways", "volume sheet - allan reeder",
					"volume sheet - damas gate (cultural snacks)", "volume sheet - baby provision",
					"volume logs quarterly spends", "breads & fruits expiry report monthly - data analyst",
					"consolidated food wastage report - monthly", "dry stock invoices log - data analyst",
					"food picture check per day data analyst", "baby provisions logs",
					"procurement or equipment log - amazon",
					"equipment log update amazon - data analyst", "security invoice logs",
					"laundry & cleaning invoices logs", "hot food survey report caterer wise - excel (per caterer)",
					"buffet meal picture check per day", "buffet picture report", "daily drops chaser (per chaser)",
					"timesheet corrections (per corrections)", "internal sheets", "margins", "stock turn",
					"monthly paperwork", "stock capacity", "daily drops sheet", "transfer fulfilment",
					"crate count 2023", "manager signs offs", "data sheet + v&a sheet", "stage 5 email",
					"facebook messages", "store update", "daily alarm check", "membership attachments",
					"ecom weekly email", "ed's sheets chaser", "discount investigation", "wage budget manager",
					"wage budget chaser", "customer service report", "ecom daily email", "lfls", "rmas",
					"daily sales investigation", "global daily", "loss update email", "week wise chaser", "store bonus",
					"new ptm sheet", "bonus calculation", "master stock turn", "p&l chaser", "ptm sheet ecom", "calls",
					"emails", "meeting", "meeting minutes", "training", "other tasks"));
			break;
		case "Incident":
			actTaskList.addAll(Arrays.asList("general irs published", "major irs published", "serious irs published",
					"irs processed", "internal irs", "ir updates", "add summary - only for irs performed by others",
					"crh queries", "move request", "maintenance report", "total incidents report", "major updates",
					"general queries", "email scanning", "closing active irs", "archiving",
					"database update", "weekly top irs (abdul's task)", "cease of movement", "wl chase",
					"weekly or monthly ir count report (internal)", "weekly ir word", "weekly ir excel",
					"weekly top food irs", "laundry logs", "observation check", "calls", "emails", "meeting",
					"meeting minutes", "handover", "training", "other tasks"));
			break;
		case "Info":
			actTaskList.addAll(Arrays.asList("service center queries", "major queries", "move request",
					"general queries - info", "email scanning - info", "official sensitive", "new arrival (including bulk upload)",
					"moving email to other folders", "archiving - info", "missing / absent in oia",
					"dispersal / eviction emails", "rms changes / modification", "lr to hotels", "proof of address",
					"chasing oia unclean rooms", "chasing ia unclean rooms", "meal absentees",
					"in-transit list resolve", "asf1", "asf-specific case barrier",
					"pre notification / appointments for asf1", "airport inn arrival / departure list", "bed makeup",
					"accessible room details", "morning ia vacancies", "morning ia vacancies email draft",
					"morning oia vacancies", "morning tranche", "morning oia email draft", "morning cardiff",
					"morning east bourn register", "mid-day oia vacancies", "mid day tranche",
					"mid-day oia email draft", "afternoon oia vacancies", "afternoon tranche",
					"afternoon oia email draft", "afternoon ia vacancies", "afternoon ia vacancies email draft",
					"afternoon cardiff", "sus staying overnight register update", "vacancies summary update",
					"isolation report", "past 20 days", "notification of arrivals (basingtoke)", "infectious overview",
					"isolation room breakdown t5", "monthly report - sus staying overnight",
					"monthly report - vacancies", "calls", "emails", "meeting", "meeting minutes", "handover",
					"training", "other tasks"));
			break;
		case "MPR":
			actTaskList.addAll(Arrays.asList("total holiday sheet update - head office / managers",
					"time correction - per staff", "active employee sheet - per staff",
					"mpr head office & manager sheet update - per staff", "device change request", "job seeker",
					"psychometric assessment", "onboarding link", "employment letter", "trust id",
					"full time-part time", "gm / staff clock in issue", "site access", "pension scheme",
					"manager's resignation", "minor payroll discrepancy", "time off approval - per request",
					"levers sheet - per staff", "staff accrued hours", "manager's leaves", "time correction emails",
					"profile update - details", "bank details update", "ssp, smp, spp",
					"day / night contract amendments", "bereavement leave", "dismissal", "visa expiry",
					"resignation & leaver's sheet update and leaver's approval", "site change",
					"major payroll discrepancy", "general complaints / general queries",
					"absence / extended leaves verifying and email", "right to work check", "site decant",
					"time off emails", "reference", "contract", "rota amendments", "james's correction report",
					"profile screening", "job posting", "assisting in emails",
					"query investigation (mickey, ayman, fayyaz, etc.)", "multiple query emails", "saf daily report",
					"accrued sheet for accounts", "report verification", "interview scheduled", "interviews", "calls",
					"emails", "meeting", "meeting minutes", "training", "other tasks"));
			break;
		case "Laundry":
			actTaskList.addAll(Arrays.asList("laundry invoice checking", "laundry count chaser to hotel",
					"laundry invoice log", "laundry ir weekly logsheet checking (per incident)",
					"laundry monthly split invoice (per entry)", "laundry invoice approval", "laundry ir",
					"laundry pending ir solving", "laundry raising issue with sp",
					"laundry allocation changes (per site)", "laundry migrant help response", "laundry weekly report",
					"laundry monthly report", "provider sheet update - laundry", "extra wash sheet verification",
					"email scanning - laundry", "calls", "emails", "meeting", "meeting minutes", "handover", "training",
					"other tasks"));
			break;
		case "Security":
			actTaskList.addAll(Arrays.asList("security invoice checking", "security monthly split invoice (per entry)",
					"security invoice log", "security guard data verification per staff", "no. of records",
					"upload docs", "security invoice approval", "security company logs", "security ir",
					"security pending ir", "security - raising issue with sp",
					"security guard allocation changes / allocations (per site)",
					"security response on migrant complaints", "security weekly report", "security monthly report",
					"authorised security sheet update (log count)", "oia allocation summary- security",
					"leavers per guard", "invoice summary per site", "provider sheet update - security",
					"rota verification with rms", "guards double entry notificatiom", "email scanning - security", "calls",
					"emails", "meeting", "meeting minutes", "handover", "training", "other tasks"));
			break;
		case "Training":
			actTaskList.addAll(Arrays.asList("deactivation account", "elfy document upload & download",
					"elfy site change/ role change", "hierarchy update per site",
					"staff checking in both portals (per site)", "new staff training link", "course reset",
					"general queries", "staff chasing mail", "recovery code", "impellus training course booking",
					"staff list report", "training report of site", "compliance report rm", "compliance report am",
					"support case single query", "support case multiple query",
					"external training research ( council training/ fgm/nhs/oral health etc)",
					"chasing sheet update (staff count)", "summary report - date wise (reports count)", "weekly report",
					"monthly report", "calls", "emails", "meeting", "meeting minutes", "training", "other tasks"));
			break;
		case "Procurement":
			actTaskList.addAll(Arrays.asList("delivery note - procurement", "amazon order - item count",
					"amazon order - order count", "stationary order - item count",
					"stationary order (including email response) - order count", "order check (baby provision)",
					"order corrected (baby provision)", "warehouse inventory log", "baby provision",
					"procurement or equipment log - amazon", "procurement or equipment log - except amazon",
					"equipment log update amazon - procurement", "equipment log update - except amazon",
					"baby provision fulfilment log", "baby provision extra log", "equipment order - online",
					"sbhl equipments data", "new site equipment order", "chat with supplier",
					"baby provision fulfilment report weekly",
					"equipments order (nisbets / appliance direct / ecatering) - email",
					"products search - (specify products, number of websites and criterias)",
					"baby provision monthly fulfillment report",
					"procurement irs / missing / shortage (including email reply & email to supplier)",
					"procurement pending ir update - (including email reply & email to supplier)",
					"procurement weekly report", "past equipment invoice check", "quatation review",
					"new supplier search (websites visited + companies contacted)", "email scanning - procurement",
					"procurement log verify & update for monthly report", "decanting site equipments verify",
					"amazon order query", "site allocation/decant emails", "calls", "emails", "meeting",
					"meeting minutes", "handover", "training", "other tasks"));
			break;
		default:
			break;
		}
		return actTaskList;
	}
}

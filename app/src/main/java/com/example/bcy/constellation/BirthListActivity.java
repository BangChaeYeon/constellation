package com.example.bcy.constellation;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

// 설화보기인가 거기에서 이미지 사이즈가 변경 되는 것
// 각 버튼에 대한 이미지 변경....
public class BirthListActivity extends Activity implements  AdapterView.OnItemSelectedListener {
    int userRight = 0; // 사용자가 올바르게 생일을 선택했는지
    int month = 1; // MainActivity.java에서 사용하는 (월) Month
    int date = 1;  // MainActivity.java에서 사용하는 (일) Date
    int imgSrc = 0; // 해당 이미지 번호 (src)

    Spinner spinner;  // activity_main.xml / spinner 왼쪽   / 월 (Month)
    Spinner spinner2; // activity_main.xml / spinner 오른쪽 / 일 (Date)

    Button dateBtn;   // activity_main.xml / 밑에           /
    Button tStoriesBtn; // 설화보기 버튼
    Button characteristicBtn; // 특징보기 버튼
    Button personBtn; // 인물(연예인) 버튼

    TextView addTxt; // 사용자가 알맞게 눌렀는지?
    TextView content; // 내용 (설화, 특징...)
    TextView txt;  // 임시 테스트용.....날짜가 잘 찍히는지
    ImageView birthImg; // 이미지의 아이디.....?

    int imgSCnt = 0;
    int images[] = {0, 0};

    ViewFlipper v_filpper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birth_list_page);

        //여기에다가 넣기
        spinner = findViewById(R.id.spinnerM);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SMonth, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        spinner2 = findViewById(R.id.spinnerD);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.SDate, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        tStoriesBtn = (Button) findViewById(R.id.tStoriesBtn);
        characteristicBtn = (Button) findViewById(R.id.characteristicBtn);
        personBtn = (Button) findViewById(R.id.personBtn);

        addTxt = (TextView) findViewById(R.id.addTxt);
        dateBtn = (Button) findViewById(R.id.dateBtn);
        birthImg = (ImageView) findViewById(R.id.b_0); // 시작할 때는 빈 화면이므로.....
        content = (TextView) findViewById(R.id.content);
        content.setMovementMethod(new ScrollingMovementMethod());
        content.setTextColor(Color.WHITE);
        addTxt.setTextColor(Color.WHITE);


        v_filpper = findViewById(R.id.imgSl);

//        images[0] = R.drawable.empty;
        for(int image: images){
            flipperImages(image);
        }

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthImg.setVisibility(View.VISIBLE);
                v_filpper.setVisibility(View.GONE);

                addTxt.setText("");
                content.setText("");
                if(month == 1){
                    if(date <= 19){ birthImg.setImageResource(R.drawable.capricornus); imgSrc =1 ;} // 1월 19일까지 => 염소
                    else{ birthImg.setImageResource(R.drawable.aquarius);  imgSrc =2;} // 1월 19일 이후=> 물병
                }else if(month == 2){
                    if(date <= 18) {birthImg.setImageResource(R.drawable.aquarius); imgSrc = 2;}
                    else if(date <= 28) {birthImg.setImageResource(R.drawable.pisces); imgSrc=3;}
                    else { userRight = 1; }
                }else if(month == 3){
                    if(date <= 20) {birthImg.setImageResource(R.drawable.pisces); imgSrc =3; }
                    else {birthImg.setImageResource(R.drawable.aries); imgSrc =4;}
                }else if(month ==4){
                    if(date <= 19) {birthImg.setImageResource(R.drawable.aries); imgSrc =4; }
                    else if( date <= 30) {birthImg.setImageResource(R.drawable.taurus); imgSrc =5;}
                    else { userRight = 1;}
                }else if(month ==5){
                    if(date <= 20) {birthImg.setImageResource(R.drawable.taurus); imgSrc = 5;}
                    else {birthImg.setImageResource(R.drawable.gemini); imgSrc =6;}
                }else if(month == 6){
                    if(date <= 21) {birthImg.setImageResource(R.drawable.gemini); imgSrc =6; }
                    else if( date <= 30)  {birthImg.setImageResource(R.drawable.cancer); imgSrc =7;}
                    else { userRight = 1; }
                }else if(month ==7){
                    if(date <= 22) {birthImg.setImageResource(R.drawable.cancer); imgSrc =7; }
                    else {birthImg.setImageResource(R.drawable.leo); imgSrc =8;}
                }else if(month ==8){
                    if(date <= 22) {birthImg.setImageResource(R.drawable.leo); imgSrc =8; }
                    else {birthImg.setImageResource(R.drawable.virgo); imgSrc =9;}
                }else if(month ==9){
                    if(date <= 23) {birthImg.setImageResource(R.drawable.virgo); imgSrc =9; }
                    else if( date <= 30)  {birthImg.setImageResource(R.drawable.libra); imgSrc =10;}
                    else {userRight = 1; }
                }else if(month ==10){
                    if(date <= 22) {birthImg.setImageResource(R.drawable.libra); imgSrc = 10;}
                    else {birthImg.setImageResource(R.drawable.scorpius); imgSrc =11;}
                }else if(month ==11){
                    if(date <= 22) {birthImg.setImageResource(R.drawable.scorpius);  imgSrc = 11;}
                    else if( date <= 30)  {birthImg.setImageResource(R.drawable.sagittarius); imgSrc = 12;}
                    else { userRight = 1; }
                }else if(month ==12){
                    if(date <= 24) { birthImg.setImageResource(R.drawable.sagittarius); imgSrc = 12;}
                    else {birthImg.setImageResource(R.drawable.capricornus); imgSrc = 1;}
                }
                // 사용자가 입력을 제대로 했는지....(텍스트)
                if (userRight == 0) {
                    addTxt.setText("");
                } else {
                    addTxt.setText("날짜를 다시 선택해주세요!");
                    birthImg.setImageResource(R.drawable.empty);
                    imgSrc = 0;
                }

                v_filpper.removeAllViews(); // v_filpper의 이미지 값 제거
                userRight = 0; // 다시 초기화

            }
        }); // DateBtn

        personBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthImg.setVisibility(View.GONE);
                v_filpper.setVisibility(View.VISIBLE);

                if(imgSrc == 1) { images[0] = R.drawable.capricorn_1; images[1] = R.drawable.capricorn_2;}
                else if(imgSrc == 2) { images[0] = R.drawable.aquarius_1; images[1] = R.drawable.aquarius_2;}
                else if(imgSrc == 3) { images[0] = R.drawable.pisces_1; images[1] = R.drawable.pisces_2;}
                else if(imgSrc == 4) { images[0] = R.drawable.aries_1; images[1] = R.drawable.aries_2;}
                else if(imgSrc == 5) { images[0] = R.drawable.taurus_1; images[1] = R.drawable.taurus_2;}
                else if(imgSrc == 6) { images[0] = R.drawable.gemini_1; images[1] = R.drawable.gemini_2;}
                else if(imgSrc == 7) { images[0] = R.drawable.cancer_1; images[1] = R.drawable.cancer_2;}
                else if(imgSrc == 8) { images[0] = R.drawable.leo_1; images[1] = R.drawable.leo_2;}
                else if(imgSrc == 9) { images[0] = R.drawable.virgo_1; images[1] = R.drawable.virgo_2;}
                else if(imgSrc == 10) { images[0] = R.drawable.libra_1; images[1] = R.drawable.libra_2;}
                else if(imgSrc == 11) { images[0] = R.drawable.scorpio_1; images[1] = R.drawable.scorpio_2;}
                else if(imgSrc == 12) { images[0] = R.drawable.sagittarius_1; images[1] = R.drawable.sagittarius_2;}

                for(int image: images){
                    flipperImages(image);
                };

                addTxt.setText("");
                if(imgSrc == 1) {
                    content.setText("1. 염소자리\n" +
                            "외강내유인 염소자리. 겉보기에는 찬바람 쌩쌩 불고 냉정하지만 속은 따뜻하고 여린 심성의 소유자다. 남자는 한 여자를 지겹도록 사랑하고, 여자는 남자를 택하는데 갖가지 조건을 적용해서 까다롭다. 황소자리를 만나면 백년해로한다.\n" +
                            "<염소자리인 나와 어울리는 연예인은?>\n" +
                            "★ 김종국, 김상혁(클릭비), 김수로, 김흥수, 봉태규\n" +
                            "★ 문근영, 이효리, 남상미, 최강희, 렉시");
                }
                else if(imgSrc == 2) {
                    content.setText("재능이 넘치고, 포용력 강한 물병자리. 남자의 경우 카사노바일 확률이 높고, 여자의 경우 사랑을 하면 모든 걸 다 버리고 헌신적이 된다. 물병자리는 쌍둥이자리와 대화가 잘 통하며 서로 기분 좋게 사귈 수 있다. \n" +
                            "<물병자리인 나와 어울리는 연예인은?>\n" +
                            "★ 토니안, 재희, 조한선, 양동근, 믹키유천(동방신기), 노민우(더트랙스), 은지원\n" +
                            "★ 최지우, 유민, 조혜련, 서지영, 송윤아, 김원희");
                }
                else if(imgSrc == 3) {
                    content.setText("지나치게 소극적이고 대담하지 못한 물고기자리. 오죽하면 평생 새우잠을 자야 할 형상이란 말도 있다. 연애에 있어서도 적극적이지 못하다. 여자의 경우 섹스테크닉 연마에 신경을 써야 도움이 된다. \n" +
                            "<물고기자리인 나와 어울리는 연예인은?>\n" +
                            "★ 비, 타블로, 지진희, 이병헌, 박준형(GOD), 공유\n" +
                            "★ 하지원, 임수정, 한지혜, 강수정, 한은정");
                }
                else if(imgSrc == 4) {
                    content.setText("허영과 사치심이 많은 양자리. 성취욕이 대단해서 한가지 일에 파고드는 집중력이 뛰어나다.남자의 경우 권위적이어서 여자에게 기피대상이 될 가능성 있고, 여자의 경우 청순 미인이라 인기가 많은 편이다. \n" +
                            "<양자리인 나와 어울리는 연예인은?>\n" +
                            "★ 조인성, 권상우, 이동건, 전진(신화), 이민우(신화), 장근석, 유재석\n" +
                            "★ 수애, 김민정, 소유진, 김민선, 윤진서");
                }
                else if(imgSrc == 5) {
                    content.setText("남을 지휘하는 카리스마적 능력이 있다. 본인 스스로도 독선적이어서 남 밑에서 일하기 힘들어 한다. 남자라면 처음 만난 여자와 키스까지 할 수 있는 대담성이 있다. 여자는 사랑을 쉽게 표현 못해서 짝사랑을 많이 한다.\n" +
                            "<황소자리인 나와 어울리는 연예인은?>\n" +
                            "★ 배용준, MC몽, 안재욱, 정경호, 이현도, 구준엽\n" +
                            "★ 한채영, 한예슬, 채정안, 정시아, 아유미(슈가), 조정린, 김혜수");
                }
                else if(imgSrc == 6) {
                    content.setText("예술적인 재능이 뛰어난 만능재주꾼. 상당히 엘리트적이기도 하고 천부적 재능도 있다. 때로는 세상을 너무 부정적으로 바라보기도 한다. 첫눈에 반하는 사랑을 믿지 않는다. 또 사랑자체를 믿지 않아서 누군가를 사귀어도 그 사람에게 잘 신뢰를 주지 못한다.\n" +
                            "<쌍둥이자리인 나와 어울리는 연예인은?>\n" +
                            "★ 원빈, 송일국, 현빈, 송승헌, 김종민(코요태), 류시원, 윤택\n" +
                            "★ 심은하, 윤은혜(베이비복스), 전혜빈, 배두나, 임은경, 이지현(쥬얼리)");
                }
                else if(imgSrc == 7) {
                    content.setText("정직하고 의협심 있는 바른 생활 게자리. 남자는 한 여자만을 가슴에 품고 사는 타입이고, 여자는 혼전순결에 대한 개념이 강한 편이다. 그래서 게자리 여자와 사귀는 상대는 스킨십에 있어서 힘들어 할 경우가 많다.\n" +
                            "<게자리인 나와 어울리는 연예인은?>\n" +
                            "★ 정우성, 이정재, 조성모, 장동건, 김래원\n" +
                            "★ 송혜교, 옥주현, 고현정, 장나라, 성유리, 김희선, 이나영");
                }
                else if(imgSrc == 8) {
                    content.setText("의지가 대쪽 같고 청렴 결백한 사자자리. 정치인이 되기에 알맞은 별자리이다. 남자는 열정적이어서 연인을 위해서 별까지 따다 주겠다고 맹세하는 타입. 여자는 현모양처감으로 내조를 하면 의외로 잘 맞는다.\n" +
                            "<사자자리인 나와 어울리는 연예인은?>\n" +
                            "★ 신혜성(신화), 윤계상, 온주완, 장혁, 천정명\n" +
                            "★ 박경림, 채연, 소이, 수진(슈가), 정혜영");
                }
                else if(imgSrc == 9) {
                    content.setText("감성이 예민하고 까다로우며 차분하다. 독서와 명상 등 조용한 시간을 즐긴다. 남자는 상대편 여자가 적극적으로 대시 해오면 쉽게 넘어간다. 여자는 이성을 만나는 걸 어려워하면서도 낭만적인 연애를 꿈꾼다. 독특한(?) 처녀자리는 자신을 잘 이해해주는 상대를 만나야 한다.\n" +
                            "<처녀자리인 나와 어울리는 연예인은?>\n" +
                            "★ 강동원, 이완, 이승기, 데니안(GOD), 이기찬, 시아준수(동방신기)\n" +
                            "★ 손예진, 강혜정, 박솔미, 장신영, 엄지원, 윤소이");
                }
                else if(imgSrc == 10) {
                    content.setText("질서를 잘 지키며 도덕적이다. 돈에 대한 욕심을 부리지않으며 객관적인 입장에서 시시비비를 따진다. 인내심이 강한 건지, 사랑에 있어서 먼저 표현하지 않는다. 불같이 사랑하면서도 상대가 알아채주기를 바란다. 남자는 애교 넘치는 여자에게 쉽게 반하며, 여자는 달콤한 사랑고백에 홀딱 넘어간다. \n" +
                            "<천칭자리인 나와 어울리는 연예인은?>\n" +
                            "★ 에릭(신화), 조승우, 유노윤호(동방신기), 영웅재중(동방신기), 최강창민(동방신기)\n" +
                            "★ 려원, 한가인, 이영애, 제니퍼, 전도연");
                }
                else if(imgSrc == 11) {
                    content.setText("침착하고 신중하며 말을 아끼는 전갈자리. 누군가 자신에게 잘못한 일을 하면 죽을 때까지 그걸 기억해둔다. 남자는 묘한 매력이 있어서 여자들에게 인기가 많다. 여자는 말수가 적은 남자에게 신비감을 느껴서 이끌린다. 같은 전갈자리와 만나면 최고의 궁합이다.\n" +
                            "<전갈자리인 나와 어울리는 연예인은?>\n" +
                            "★ 소지섭, 세븐, 김동완(신화), 연정훈, 이정\n" +
                            "★ 전지현, 보아, 박한별, 신지(코요태), 린, 구혜선");
                }
                else if(imgSrc == 12) {
                    content.setText("저돌적으로 일을 밀고 나가는 사수자리. 추진력은 있으나 때론 곰같이 미련하다는 말도 듣는다. 사수자리 남자는 성욕이 강하고 세심함이 없다. 그래서 연애할 때 여자에게 점수를 많이 못 딴다. 여자의 경우 자유로운 성격이 강해서 개방적이고 자상한 남자를 원한다.\n" +
                            "<사수자리인 나와 어울리는 연예인은?>\n" +
                            "★ 손호영(GOD), 엄태웅, 오지호, 유지태, 차태현, 제이킴(더트랙스)\n" +
                            "★ 김태희, 신민아, 변정수, 이진, 채림");
                }
            }
        });// 인물


        characteristicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthImg.setVisibility(View.VISIBLE);
                v_filpper.setVisibility(View.GONE);
                addTxt.setText("");
                   if(imgSrc == 1) {
                   content.setText("염소자리는 묵묵하게 자신의 일을 해내는 타입입니다.\n" +
                           "의지가 강한 성격으로 달성하고자 하는 목표는 반드시 달성하는 성격입니다.\n" +
                           "부지런하고 검소한 타입으로 내실이 있는 성격으로 스스로 절제할 수 압니다.\n" +
                           "개인주의적이고 비현실적인 것을 싫어합니다.\n" +
                           "염소자리를 화술이 뛰어나지 않으므로 주위사람들에서 가시돋힌 말로 성처를 줄 수 있으니 언행에 항상 조심하시길 바랍니다.\n" +
                           "목표지향적으로 리더적 성격을 갖고 있고 규칙과 질서를 중요시 여겨 가끔은 융통성이 없다는 소리도 듣습니다.\n" +
                           "눈 앞에 이익을 쫓지 말고 멀리보세요.");
               } // 1
                else if(imgSrc == 2) {
                    content.setText("물병자리른 판단을 잘합니다.\n" +
                            "평소 나서는 것을 좋아하지 않지만 중요한 순간이나 자신이 꼭 나서야 할 순간에는 적극적으로 나서는 성격입니다.\n" +
                            "자신의 목적을 위해서라면 약간의 희생도 감수할 수 있는 성격이라고 합니다.\n" +
                            "침착하고 개인주의적 성격이 강하고 독창적인 사람들이 많다고 합니다.\n" +
                            "공정하고 친절하지만 공과 사 구분은 굉장히 뚜렷하며 금전 욕심보다는 사람을 더 아끼는 성격입니다.\n" +
                            "약간은 고지식하고 행동력이 넘친다고 하네요.\n" +
                            "판사나 변호사 같은 직업군이 굉장히 잘 맞는다고 합니다.");
                } // 2
               else if(imgSrc == 3) {
                   content.setText("물고기자리의 감성은 마치 롤러코스터 같습니다.\n" +
                           "기분파이기 때문에 순간의 결정이 달라질 수 있습니다.\n" +
                           "물고기 자리인 사람에게 기분만 잘 맞춰준다면 원하는 것을 얻을 수 있다고 합니다.\n" +
                           "하지만 물고기 자리의 이러한 성격을 이용하여 너무 속보이게 자신의 이익을 챙기려고 하지 마세요.\n" +
                           "물고기 자리는 눈치가 ㅃ라라 타인의 생각을 꿰뚫고 있다고 합니다.\n" +
                           "알면서도 속아주는 경우가 대부분이라고 하네요.\n" +
                           "자기 자신의 세계관을 가지고 있어 사차원이라는 소리를 많이 듣습니다.\n" +
                           "끊임없이 생각하는 스타일이므로 상처를 잘 받고 또 오래가는 스타일이라고 합니다.\n" +
                           "예술적 성향이 높아 예체능계열의 사람이 많다고 하네요.\n" +
                           "분위기를 중시하는 타고나 연인에게 언제나 이벤트를 해주는 로맨티스트입니다.");
               } // 3
               else if(imgSrc == 4) {
                   content.setText("양자리는 자신의 목표가 뚜렷한 사람입니다.\n" +
                           "그렇기 때문에 일을 성실히 하는 타입이라고 합니다.\n" +
                           "한 번에 목표를 달성하기보다는 꾸준한 노력으로 자신의 목표를 이룰 성격이라고 합니다.\n" +
                           "다소 자기 중심적이며 직설적이고 또 자신을 간섭하는 사람을 굉장히 싫어한다고 합니다.\n" +
                           "생각이 얼굴로 드러나 기분을 알기쉽다고 합니다.\n" +
                           "경쟁심이 강하고 남 몰래 질투가 많지만 상대와 연을 끊을 만큼 싸우거나 감정을 상하게 하지 않는다고 합니다.\n" +
                           "대인관계를 굉장히 중요시 여긴다고 하네요.\n" +
                           "창의적이고 자립심이 강해 자수성가하는 사람이 많다고 합니다.");
               } // 4
               else if(imgSrc == 5) {
                   content.setText("황조라리는 발고 타인에게 인기가 많다고 합니다.\n" +
                           "가끔은 말장난도 치지만 선을 지킨다고 하네요.\n" +
                           "호탕한듯 보이지만 속으로 현실적이며 얀간은 계산적이라고 하네요.\n" +
                           "안전을 중요시하는 타입으로 도전하는 것을 별로 즐기지 않고 현재에 안주하려는 성격이 강하다고 합니다.\n" +
                           "하지만 확실히 안전하다고 생각되면 그것에 몰두하는 스타일입니다. \n" +
                           "신중하면서 현실성이 강합니다.");
               } // 5
               else if(imgSrc == 6) {
                   content.setText("두뇌회전이 굉장히 빠르고 행동 민첩합니다.\n" +
                           "즉 눈치가 빠르다고 하네요\n" +
                           "상황을 잘 파악하므로 이 능력을 잘 활용한다면 주위에서 인정을 받을 것입니다.\n" +
                           "사교성이 매우 높고 유머감각과 기재까지 넘칩니다.\n" +
                           "호기심이 많고 다재다능해서 사람들에게 인기가 넘친다고 합니다.\n" +
                           "새로운 환경에 잘 적응하여 실수없이 일을 잘한다고 하네요.\n" +
                           "하지만 머리가 좋고 일을 빠르게 잘하다보니 주위에서 보면 설렁설렁 한다는 오해를 받을 수도 있습니다.\n");
               } // 6
               else if(imgSrc == 7) {
                   content.setText("게자리는 연인에게 굉장히 헌신적이라고 하네요.\n" +
                           "하지만 헌신하다간 헌신짝처럼 버려질 수도 있으니 조심!\n" +
                           "애정과 배려심이 넘치면 가정적입니다.\n" +
                           "이것저것 잘하는 것이 많습니다.\n" +
                           "여러가지에 재능이 많아 주위에서 시기를 받을 수도 있다고 합니다.\n" +
                           "여러 사람을 사귀기보다는 소수의 사람을 사귀고 깊게 사귄다고 합니다.\n" +
                           "게자리 사람은 길게 알면 알수록 좋은 사람이라고 합니다.");
               } // 7
               else if(imgSrc == 8) {
                   content.setText("사자자리를 표현하는 단어 \n" +
                           "'야망'\n" +
                           "능력을 굉장히 중요시 합니다.\n" +
                           "자신이 능력있고 인정받는 것을 좋ㅇ하고 중요하게 여기기 때문에 스스로 뒤쳐지거나 약자가 되는 것을 되는 것을 참을 수 없습니다.\n" +
                           "그러기 때문에 끊임없이 노력하는 스타일입니다.\n" +
                           "하지만 야망이 너무 높기 때문에 쉴새없이 평생 노력만 하는 스타일이라 심신이 쉽게 지친핟고 하니 약간의 휴식을 갖는 것도 좋다고 합니다.\n" +
                           "늘 주인공이 되는 것을 좋아하는 성격입니다.\n" +
                           "요즘마로 관종이라고 하는 사람이 많은 타입이기도 합니다.\n" +
                           "다재다능하지만 자기 고집이 강하고 호불호가 확실하면 남의 말은 잘 듣지 않기 때문에 \n" +
                           "주위에 자기주장이 강한 사람보다는 말을 잘 들어 주는 친구들이 많다고 하네요.");
               } // 8
               else if(imgSrc == 9) {
                   content.setText("처녀자리는 별자리에 두뇌를 나타내는 별을 가지고 있는 별자리입니다.\n" +
                           "그렇기 때문에 머리가 좋은 사람이 많지요. 꼼꼼하고 섬세한 성격입니다.\n" +
                           "한 번 친해지면 다른 사람에게 정을 잘 나누는 마음이 따뜻한 사람입니다.\n" +
                           "책임감이 강하며 완벽을 추구해 자신이 정한 룰에 딱 맞춰 생활하는 것을 좋아합니다.\n" +
                           "다이어라나 일정표를 정리하는 것을 좋아한다고 합니다.\n" +
                           "남에게 튀는 것을 꺼리는 편이라 조용한 성격을 가지고 있는 처녀자리.\n" +
                           "두루두루 친한 것보다는 자신에게 맞는 성격의 친구를 사귀는 것을 선호합니다.");
               } // 9
               else if(imgSrc == 10) {
                   content.setText("천칭자리를 앞에서 말했던 처녀자리와 다르게 친구를 두루두루 사귀는 타입입니다.\n" +
                           "친구가 많긴 하지만 적당한 거리감을 두고 깊게 사귀진 않는다고 하네요.\n" +
                           "조용한 성격으로 모범생 타입의 성격입니다.\n" +
                           "눈에 띄는 타입은 아니지만 주위 사람도 원만한 관계를 유지합니다.\n" +
                           "문제를 일으키는 것을 싫어하고 실제로도 문제를 잘 일으키지도 않습니다.\n" +
                           "사교성이 좋고 중재자 역할을 자주 맡아 누구에게나 사랑을 받는 타입입니다.\n" +
                           "사람과 깊게 사귀질 않으나 인기가 많아 바람둥이도 많으며 자신을 꾸미는 것을 좋아해 외모에도 신경을 많이 씁니다.\n" +
                           "약간의 허영심이 있을 수도 있다고 하네요.");
               } // 10
               else if(imgSrc == 11) {
                   content.setText("전갈자리는 본인의 아이덴티티가 확실한 자리입니다.\n" +
                           "자아가 굉장히 강해 강한 인상을 남긴다고 합니다.\n" +
                           "자존심이 매우 강하며 융통성은 없다고 하네요.\n" +
                           "독불장군 스타일이라고 합니다.\n" +
                           "추진력이 좋고 원하는 수준까지 일을 마무리해야 직성이 풀리는 타입입니다.\n" +
                           "목표를 이루기 위해서는 뒤돌아보지 않고 앞으로 달려가는 성격으로 사소한 것부터 중요한 것까지 일관적이 태도로 임합니다.\n" +
                           "겉은 온순해보이지만 내면은 야망으로 가득차있습니다.\n" +
                           "남을 잘 믿지 않는 경계심이 많은 타입이며 중도 포기를 모르는 성격입니다.\n" +
                           "불같은 성격으로 이성이 바람을 피운다면 무서운 복수를 한다고 합니다.");
               } // 11
               else if(imgSrc == 12) {
                   content.setText("궁수자리를 표현하는 단어\n" +
                           "'자유'\n" +
                           "솔직한 성격이고 어려운 상황에 구애 받지 않고 자유롭다고 합니다.\n" +
                           "주위 사람을 잘 돌보고 신경쓰기 때문에 주위에서 인기가 많습니다.\n" +
                           "의리가 좋은편이라고 하네요.\n" +
                           "자신의 일보다 남을 더 잘 돌보려하기 때문에 다소 앞가림을 못한다는 소리도 듣지만 주위의 평가는 좋은 편입니다.\n" +
                           "연인으로서는 최고이지만 배우자로써는 인기가 없는 타입이라고 하네요.\n" +
                           "초낙관주의,자유분방한 성격의 소유자로 진지한 것을 좋아하지 않고, 운동신경이 발달해서 스포츠를 좋아하며 굉장히 솔직한 성격의 소유자입니다.");
            } // 12
               else {
                   content.setText("");
               }
            }
        }); // characteristicBtn


        tStoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthImg.setVisibility(View.VISIBLE);
                v_filpper.setVisibility(View.GONE);

                addTxt.setText("");
                if (imgSrc == 1) {
                    birthImg.setImageResource(R.drawable.tstoriescapricorn);
                    content.setText("염소자리   \n" +
                            "\n" +
                            "(12월21일~1월20일)\n" +
                            "\n" +
                            "풀과 들의 신이며 또한 양떼나 양치기의 신인 판은 어느날 다른 신들과 어울려 나일강가에서 연회를 즐기고 있을 때 갑자기 무서운 거인족 티폰이 나타나 그들을 공격하기 시작했습니다.\n" +
                            "\n" +
                            "놀란 신들이 동물들의 모습으로 변신하여 도망치기 시작했습니다. 판도 주문을 외우면서 물 속으로 뛰어 들었으나 너무 서두르는 바람에 주문이 섞여 상반신은 염소로 변하고 하반신은 물고기모습으로 변하고 말았습니다.\n" +
                            "\n" +
                            "다시 주문을 바꾸려는 순간 티폰에게 붙잡혀 비명을 지르는 제우스의 음성을 듣고 판은 주문을 외울 사이도 없이 그가 발명한 풀피리를 크게 불었습니다. 풀피리의 처절한 소리에 놀란 티폰은 제우스를 놓아둔 채 도망치고 말았습니다.\n" +
                            "\n" +
                            "판의 재치있는 도움으로 살아난 제우스는 그에 대한 보답으로 하늘의 별들 속에 반양반어인 바다염소를 만들어 판의 도움을 영원히 기억되게 하였습니다.그리스인들은 이 별자리를 '판의 별자리'라고 부르고 있는데, 이것은 위와 같은 신화에서 비롯된 것입니다.\n");
                } // 1
                else if (imgSrc == 2) {
                    birthImg.setImageResource(R.drawable.tstoriesaquarius);
                    content.setText("물병자리 \n" +
                            "(1월20일~2월18일)\n" +
                            "\n" +
                            "옛날 트로이에 아주 멋있는 왕자님이 살고 있었다.\n" +
                            "\n" +
                            "얼마나 잘생겼던지 나라의 모든 처녀들이 입을 헤 벌렸더랬다.\n" +
                            "\n" +
                            " 미녀들은 그 가니메데 왕자와 결혼하는 것이 최대의 꿈이었다.\n" +
                            "\n" +
                            "어느 날 하늘에서 연회가 벌어졌다.\n" +
                            "\n" +
                            "그 때 제우스 신에게 술을 따라주는 일을 하는 여자가 발목을 다치게 되어 더 이상\n" +
                            "\n" +
                            "그 일을 할 수 없게 되었다. 제우스는 다른 사람을 찾아보았지만 마음에 드는 사람이 없었다.\n" +
                            "\n" +
                            "많은 신들이 제우스에게 다른 여자를 소개해 주었지만 모두 제우스의 눈에 차지 않았던 것이다.\n" +
                            "\n" +
                            "그러던 중 하루는 아폴로가 트로이에 갈 일이 있었다.\n" +
                            "\n" +
                            "거기서 왕자가 궁녀들과 노는 것을 본 아폴로는 깜짝 놀랐다.\n" +
                            "\n" +
                            " '세상에 이렇게 멋있는 왕자가 있다니!'\n" +
                            "\n" +
                            "아폴로는 하늘로 올라와서 제우스에게 트로이에서 보았떤 가니메데 왕자에대해 말해주었다.\n" +
                            "\n" +
                            "호기심이 생긴 제우스는 트로이로 가서 가니메데 왕자를 보았다.\n" +
                            "\n" +
                            "정말 잘생긴 왕자였다. '음, 저 녀석을 데려다가 술 따르는 일을 시켜야겠군.'\n" +
                            "\n" +
                            "이렇게 마음 먹은 제우스는 큰 독수리로 변해 왕자가 혼자 있을 때를 틈타 단번에\n" +
                            "\n" +
                            "그를 낚아채서 하늘로 올라와버렸다.\n" +
                            "\n" +
                            " 왕자는 졸지에 제우스의 하인이 되어 술 따르는 일을 하게 되었다.\n" +
                            "\n" +
                            " 그러나 왕자는 고향과 식구들 생각에 하염없이 눈물을 흘렸다.\n" +
                            "\n" +
                            "트로이의 국왕도 왕자를 보고 싶어했지만 어디로 가는지도 알 수 없었다.\n" +
                            "\n" +
                            "왕자가 너무 슬퍼하고 트로이의 국왕도 왕자를 애타게 그리워하자 제우스는 미안한 생각이 들었다.\n" +
                            "\n" +
                            "그래서 트로이의 국왕에게 사실을 얘기하고 위로의 뜻으로 신마 몇 마리를 보내주었다.\n" +
                            "\n" +
                            "왕자도 성으로 보내 국왕을 만나게 했다.\n" +
                            "\n" +
                            "그러고는 왕자를 다시 하늘로 데려갔다.\n" +
                            "\n" +
                            "이렇게 해서 잘생긴 가니메데 왕자는 물병이 되어 술을 따르게 된 것이다.");
                } // 2
                else if (imgSrc == 3) {
                    birthImg.setImageResource(R.drawable.tstoriespisces);
                    content.setText("물고기자리 \n" +
                            "(2월19일~3월20일)\n" +
                            "\n" +
                            "미의 여신 아프로디테는 사랑의 신이자 자신의 사랑하는 아들인 큐피드와 우아하고\n" +
                            "\n" +
                            "정중한 옷을 입고 신들의 연회에 참석했다.\n" +
                            "\n" +
                            "신들의 연회에는 당연히 모든 여신들이 자신의 외모를 뽐내려고\n" +
                            "\n" +
                            "애 물고기자리 (2월19일~3월20일) 미의 여신 아프로디테는\n" +
                            "\n" +
                            "사랑의 신이자 자신의 사랑하는 아들인 큐피드와 우아하고\n" +
                            "\n" +
                            "정중한 옷을 입고 신들의 연회에 참석했다.\n" +
                            "\n" +
                            "신들의 연회에는 당연히 모든 여신들이 자신의 외모를 뽐내려고 애썼다.\n" +
                            "\n" +
                            "그러나 그 모습이 너무나 아름다워서 누가 가장 아름답다 할 수 없을 정도였다.\n" +
                            "\n" +
                            "남신들은 한 손에 술잔을 든 채로 삼삼오오 모여서 우주의 생성과 신들이\n" +
                            "\n" +
                            "인간에게 해야 할 일들을 의논하고 있었고,\n" +
                            "\n" +
                            " 따분한 것을 싫어하는 아이들은 이미 술래잡기에 열중하고 있었다.\n" +
                            "\n" +
                            "연회장의 분위기는 향기로운 술과 맛있는 향을 내는 음식들로 인해 최고조에 달했다.\n" +
                            "\n" +
                            "그런데 갑자기 찾아온 불청객 때문에 평화롭고 화려한 파티가 깨져버렸다.\n" +
                            "\n" +
                            "이 불청객은 추악한 외모에 악독한 마음을 가지고 있는 괴물 티폰이었다.\n" +
                            "\n" +
                            "그가 연회장에 온 목적은 바로 이 연회를 방해하기 위해서였다.\n" +
                            "\n" +
                            "그는 음식이 차려진 상을 뒤집고, 아름다운 꽃들이 피어있는 화분을 분수대로 내던져버렸다.\n" +
                            "\n" +
                            " 그리고 무서운 얼굴로 연회장에 모인 모든 신들을 두려움에 떨게 만들었다.\n" +
                            "\n" +
                            "신들은 모두 사방으로 도망가기 시작했고, 화려하던 연회장은 아수라장이 되어버렸다.\n" +
                            "\n" +
                            "비명과 아이들의 울음소리가 하늘 저 멀리까지 울려퍼졌다.\n" +
                            "\n" +
                            "이 때 아프로디테는 자신의 아들 큐피드가 없어진 것을 발견하고는\n" +
                            "\n" +
                            "그 불청객의 존재를 까마득히 망각하고 사방으로 찾아 헤맸다.\n" +
                            "\n" +
                            "구석구석 찾던 끝에 피아노 아래서 울고 있는 아들을 재빨리 품에 안았다.\n" +
                            "\n" +
                            "그리고 또 다시 큐피드와 헤어지는 걸 막기 위해 자신의 발과 아들의 발을 묶고는\n" +
                            "\n" +
                            "물고기로 변해 그 무서운 불청객의 손에서 벗어나게 된 것이다. \n" +
                            "\n" +
                            "미의 여신 아프로디테와 사랑의 신에로스는 부모와 자식이었습니다.\n" +
                            "\n" +
                            "괴물 테혼에 습격당한 두 명은, 물고기에 모습을 바꾸고 강에 뛰어들었습니다.\n" +
                            "\n" +
                            "2마리의 물고기는 놓치지 않게 끈으로 제대로 연결되고 있습니다.\n" +
                            "\n" +
                            "물고기 자리는 가을의 밤하늘에 빛나고 있습니다.");
                } // 3
                else if (imgSrc == 4) {
                    birthImg.setImageResource(R.drawable.tstoriesaries);
                    content.setText("양자리  \n" +
                            "\n" +
                            "(3월 20일~4월 20일)\n" +
                            "\n" +
                            "옛날 그리스의 테살리에 아타마스라고 불리는 왕이 살고 있었습니다.\n" +
                            "\n" +
                            "그에게는 프릭수스와 헬레라는 두 남매가 살고 있었는데 어릴 적 어머니가 돌아가셔서\n" +
                            "\n" +
                            "계모의 품에서 자라게 되었습니다. 이 계모는 성질이 사악하여 두 아이들에게\n" +
                            "\n" +
                            "잔인한 짓을 하였습니다. \n" +
                            "우연히 이곳을 지나던 전령의 신 헤르메스는 고통을 받고 있는 남매를 보고\n" +
                            "\n" +
                            "구해 줄 것을 결심하게되어 하늘로 돌아가 숫양 한마리를 아이들에게 보내\n" +
                            "\n" +
                            "행복한 곳으로 보내려했습니다. 아이들을 등에 태운 양은 하늘을 날아 동쪽으로 향했습니다.\n" +
                            "\n" +
                            "그러나 어린 헬레가 그만 양의 등에서 떨어지고 말았습니다. 헬레가 떨어진 곳은\n" +
                            "\n" +
                            "아시아와 유럽의 경계가 되는 해협이었는데 사람들은 불쌍한 헬레가 떨어진 곳을\n" +
                            "\n" +
                            "헬레스폰트라고 불렀습니다.\n" +
                            "\n" +
                            "\n" +
                            "양은 프릭수스만을 태운 채 계속 날아 콜키스라는 나라에 도착했습니다.\n" +
                            "\n" +
                            "이곳의왕 에테스는 프릭수스를 환대하고 후한 대접을 해주었습니다. 프릭수스는\n" +
                            "\n" +
                            "감사의 뜻으로 황금양을 제우스신에게 바치고 양의 황금양피는 에테스 왕에게\n" +
                            "\n" +
                            "선물하였습니다. 에테스 왕은 이 황금 양피를 잠자지 않는 용에게 지키게 하였고\n" +
                            "\n" +
                            "제우스는 이 양의 공로를 치하하여 하늘의 별자리로 만들어 주었습니다");
                } // 4
                else if (imgSrc == 5) {
                    birthImg.setImageResource(R.drawable.tstoriespisces);
                    content.setText("황소자리 \n" +
                            "\n" +
                            "(4월20일~5월21일) \n" +
                            "강의 신 이나쿠스에게 이오라는 예쁜 딸이 있었는데 제우스는 그녀를 매우 귀여워했습니다.\n" +
                            "\n" +
                            "어느날 제우스와 이오가 같이 있을때 헤라가 갑자기 나타나자 헤라에게 들킬것같아\n" +
                            "\n" +
                            "제우스는 이오를 작은 암소로 변신시켰습니다.\n" +
                            "\n" +
                            "그러나 헤라는 이 소가 이오라는 것을 알고 제우스에게 이 암소를 자신에게 줄것을\n" +
                            "\n" +
                            "부탁했습니다. 제우스는 할 수 없이 이오를 헤라에게 주었고 헤라는 눈이 백개가\n" +
                            "\n" +
                            "달린 알고스라는 괴물에게 이 암소를 감시하게 하고 밤낮으로 괴롭혔습니다.\n" +
                            "\n" +
                            "보다못한 제우스는 헤르메스를 보내 알고스를 물리치라고 명합니다. 헤르메스는\n" +
                            "\n" +
                            "양치기로 변신하여 풀피리를 불며 그에게 접근했습니다. 풀피리의 아름다운\n" +
                            "\n" +
                            "소리에 반한 알고스는 헤르메스를 불러 풀피리소리를 들었습니다. 이윽고\n" +
                            "\n" +
                            "알고스의 눈이 하나씩 감기기 시작했습니다. 그러나 알고스의 마지막 한 개의\n" +
                            "\n" +
                            "눈이 감기지 않고 계속 이오를 감시했습니다.\n" +
                            "\n" +
                            "헤르메스는 알고스에게 풀피리가 만들어진 이야기를 들려주었습니다. 시링크스와\n" +
                            "\n" +
                            " 판의 아름다운 이야기를 알고스에게 들려주자 마침내 알고스의 마지막 눈마져 감기고 말았습니다. \n" +
                            "알고스가 깊은 잠에 들자 헤르메스는 칼을 들어 알고스를 죽였습니다. 헤라는\n" +
                            "\n" +
                            "알고스가 죽은 것을 알고 알고스의 눈을 떼어 자신이 아끼던 공작새의 꼬리에\n" +
                            "\n" +
                            " 붙였습니다. 그후 헤라는 이오를 더욱 괴롭혔는데 결국 보다못한 제우스는\n" +
                            "\n" +
                            "헤라에게 사과하고 이오를 다시 인간으로 만들어 주었습니다.");
                } // 5
                else if (imgSrc == 6) {
                    birthImg.setImageResource(R.drawable.tstoriesgemini);
                    content.setText("쌍둥이자리  \n" +
                            "\n" +
                            "(5월21일~6월 21일)\n" +
                            "\n" +
                            "그리스 신화에 의하면, 카스토르(Castor)와 폴룩스(Pollux)는 백조로 변한 제우스가\n" +
                            "\n" +
                            "스파르타(Sparta)의 왕비 레다(Leda)를 유혹하여 낳은 쌍동이 형제이다.\n" +
                            "\n" +
                            "이들은 또한 트로이전쟁(The Trojan War)의 원인이 되었던 미모의 헬렌(Helen)과\n" +
                            "\n" +
                            "남매지간인 것으로도 유명하다.\n" +
                            "\n" +
                            "이들 쌍동이 형제는 신의 아름답게 강한 힘과 용기를 가지고 있었으며 당대의 최고\n" +
                            "\n" +
                            "선생님들에게 교육을 받아 모든 면에서 남들을 능가하였다. 특히 카스트로는\n" +
                            "\n" +
                            "말타기에 능했고 폴룩스는 권투와 무기 다루기에 독특한 재능을 가지고 있었다.\n" +
                            "\n" +
                            "또한 동생 폴룩스는 불사신의 몸을 가지고 있는 것으로 알려져 있다.\n" +
                            "\n" +
                            "이들은 황금양피(Golden Fleece)를 찾아나섰던 아르고(Argonauts) 호의 일행으로\n" +
                            "\n" +
                            "이 항해를 통하여 항해자와 모험가의 수호신으로 명성을 얻게 되는데 그 이야기는\n" +
                            "\n" +
                            "다음과 같이 전해진다.\n" +
                            "\n" +
                            "황금양피를 찾으러 떠난 아르고 호가 항해도중 갑작스러운 폭풍을 만나 배가\n" +
                            "\n" +
                            "흔들리고 파도가 넘쳐 사람들의 목숨이 위태롭게 된 적이 있었다. 이때 폭풍을\n" +
                            "\n" +
                            "멈추게 하기 위하여 아폴로신의 아들이자 음악의 천재인 오르페우스(Orpheus)가\n" +
                            "\n" +
                            "그 지역을 관장하는 신들에게 기도를 올리고 하프를 뜯었다. 그러자 갑자기\n" +
                            "\n" +
                            "폭풍우가 멎으면서 바다가 잠잠해지기 시작했는데 이 순간 하늘의 구름이 걷히고\n" +
                            "\n" +
                            "카스토르와 폴룩스의 머리 위로 별들이 나타나 영롱하게 빛을 발하는 것이었다.\n" +
                            "\n" +
                            "이것을 본 아르고호의 사람들은 쌍동이 형제가 하프소리에 감동하여 폭풍이\n" +
                            "\n" +
                            "멎은 것이라 생각하고 이들을 항해자와 모험가의 수호신으로 여기게 되었다.\n" +
                            "\n" +
                            "아르고 호의 원정이 있은 후에 이들 형제는 아름다운 두 자매를 차지하기 위하여\n" +
                            "\n" +
                            "그 아가씨들의 약혼자와 싸움을 하게 되었다. 이 싸움에서 불사신의 몸을\n" +
                            "\n" +
                            "가진 폴룩스는 상처 하나 입지 않고 무사할 수 있었으나 카스트로는 심한 부상을\n" +
                            "\n" +
                            "당해 결국 죽고 말았다. 폴룩스는 자신의 분신과도 같던 카스토르가 죽자 그 슬픔을\n" +
                            "\n" +
                            "감당하지 못하고 자신도 죽으려 하였지만 불사신의 몸을 가지고 있었기 때문에\n" +
                            "\n" +
                            "마음대로 죽을 수도 없었다. 결국 폴룩스는 아버지 제우스를 찾아가 자신의 죽음을 부탁했다.\n" +
                            "\n" +
                            "제우스는 이들 형제의 우애에 감동하여 이들이 하루의 반은 지하세계에서 나머지 반은\n" +
                            "\n" +
                            "지상에서 함께 지낼 수 있게 허락했다. 그리고 이들 형제의 우애를 영원히 기리기\n" +
                            "\n" +
                            "위해 이들의 영혼을 하늘에 올려 나란히 두 개의 밝은 별로 만들어 주었다.");
                } // 6
                else if (imgSrc == 7) {
                    birthImg.setImageResource(R.drawable.tstoriescancer);
                    content.setText("게자리 \n" +
                            "\n" +
                            "(6월 21일~7월22일)\n" +
                            "\n" +
                            "게자리(Cancer)는 그리스 신화의 가장 뛰어난 영웅 헤라클레스의 발에 밟혀 죽은 불쌍한 게의 별자리로 전해지고 있다.\n" +
                            "\n" +
                            " 옛날 헤라클레스가 에우리테우스(Eurysthdus) 왕의 속박에서 풀려나기 위하여 열두 가지의 고역을 겪었다. 그 중 두번째가 네메아(Nemea) 계곡의 괴물  히드라(Hydra)를 퇴치하는 것이었다.\n" +
                            "\n" +
                            " 헤라클레스는 히드라를 잡기위해 네메아 계곡에서 물뱀과 30일에 걸친 대혈 전을 벌였다. 이 때 헤라클레스를 미워했던 헤라(Hera) 여신이 물뱀을 돕기 위해 게(Cancer) 한 마리를보냈다. 게는 여신의 명령에 따라 물뱀과 싸우고 있는 헤라클레스의 발가락을 물었는데, 결국은 그의 발에 밟혀 한쪽 발이 부러진 채 죽고 말았다.\n" +
                            "\n" +
                            " 헤라는 자신을 위해 싸우다 죽은 이 불쌍한 게에 대한 보답으로 그 시체를 하늘에 올려 별자리가 되게 해주었다. 그러나 한쪽 다리를 잃은 불쌍한 게의 시체는 하늘에서도 어두운별로 꾸며졌기 때문에 밝은 별들 틈에서 잘 보이지 않는 채로 쓸쓸하게 지금까지 남아있다");
                } // 7
                else if (imgSrc == 8) {
                    birthImg.setImageResource(R.drawable.tstoriesleo);
                    content.setText("사자자리  \n" +
                            "\n" +
                            "(7월 22일~8월23일)\n" +
                            "\n" +
                            "아주 먼 옛날 하늘이 혼통 혼란 속에 빠져 별들이 그들의 자리를 떠나고 혜성이 하늘을 날아다니던 적이 있었다. 이 때 달에서 불타는 유성 하나가 황금사자의 모습으로 그리스의 네메아(Nemea) 골짜기에 떨어졌다. 유성이 변하여 된 이 사자는 지구의 사자보다 몸집이 훨씬 컸고 성질 또한 포악해서 네메아 사람들에게 많은 고통을 주었다.\n" +
                            "\n" +
                            "사자는 날로 포악해졌지만 네메아사람들의 힘으로는 이 사자를 어쩔 수가 없었다. 마침내 이 나라를 다스리는 에우리테우스(Eurystheus) 왕은 헤라클레스에게 사자를 처치할 것을 명령했다.\n" +
                            "\n" +
                            "네메아 골짜기에 나타난 헤라클레스는 활과 창, 방망이 등을 들고 사자와 싸웠지만 사자를 무찌를 수 없었다. 결국 헤라클레스는 무기를버리고 사자와 뒤엉켜 생사를 가르는 대격투를 벌이게 되었고, 신의 아들답게 사자를 궁지로 몰아넣어갔다. 끝내는 헤라클레스의 힘을 당해내지 못하고 목이 졸려 죽고 말았다.\n" +
                            "\n" +
                            "이렇게 하여 네메아의 사람들은 사자의 공포에서 벗어나 평온을 되찾았고 헤라클레스는 승리의 대가로 어떤 무기로도 뚫을 수 없는 불침의 사자 가죽을 얻게 되었다.\n" +
                            "\n" +
                            "신의 왕 제우스(Zeus)는 아들 헤라클레스의 승리를 치하하고 그의 영웅적 행동을 영원히 모든 사람들에게 기억하게 하기 위하여 사자를 하늘의 별자리로 만들었다");
                } // 8
                else if (imgSrc == 9) {
                    birthImg.setImageResource(R.drawable.tstoriesvirgo);
                    content.setText("처녀자리 \n" +
                            "\n" +
                            "(8월23일~9월23일)\n" +
                            "\n" +
                            "어느 맑게 개인 가을날 지하세계의 지배자인 하데스(Hades)가 땅위의 옥수수밭을 거닐고 있었다. 하데스는 마침 그곳에 나와 있던 어여쁜 페르세포테(Persephone)를 발견하고, 그녀의 아름다움에 매료되어 그 자리에서 페르세포네를 자신의 마차로 납치했다.\n" +
                            "\n" +
                            "땅의 갈라진 틈을 통해 자신의 지하세계로 내려간 하데스는 거기서 울며 사정하는 페르세포네를 강제로 자신의 아내로 맞이하였다. 그곳에서의 생활은 부족할 것 없이 그녀를 만족시켜 주었지만 그녀는 땅위의 언덕과 계곡, 드넓은 평원을 생각할 때마다 깊은 슬픔에 빠지곤 하였다.\n" +
                            "\n" +
                            "그녀가 지하세계로 납치된 후 딸을 잃은 토지의 여신 데메테르(Demeter)는 슬픔으로 인해 큰 비탄 속에 빠져 버렸다. 토지의 여신이 슬퍼하지 땅은 메말라갔고, 들에서는 곡식이 이삭을 패지 못했다.\n" +
                            "\n" +
                            "신들의 제왕인 제우스는 땅이 황폐해가는 것을 더 이상 방관할 수가 없었다. 그렇다고 지하세계의 왕인 자신의 형 하데스를 함부로 대할 수도 없었기에 이들을 화해시키는 방향으로 일을 만들었다. 결국 제우스의 중재로 페르세포네는 일 년의 반 동안만 지하세계에서 머무르고 나머지 반 동안은 지상에서 지낼 수 있게 되었다. 그렇게 하여 페르세포네는 매년 봄이면 하늘의 별자리가 되어 지하세계로부터 동쪽 하늘로 올라오게 되었다.\n" +
                            "\n" +
                            "그 후로 겨울에는 추위가 닥쳐오고 풀이 돋아나지 않게 되었는데 이것은 토지의 여신 데메테르가 지하세계에 있는 딸을 그리워하여 슬픔에 빠져 있기 때문이라고 한다. 그리고 새 봄이 와서 땅속으로부터 페르세포네가 나타나게 되면 데메테르의 슬픔이 가시게 되어 땅은 다시 활기를 띠고 무성한 마뭇잎과 열매를 맺게 된다고 한다.");
                } // 9
                else if (imgSrc == 10) {
                    birthImg.setImageResource(R.drawable.tstorieslibra);
                    content.setText("천칭자리 \n" +
                            "\n" +
                            "(9월23일~10월23일)\n" +
                            "\n" +
                            "먼 옛날 지상에는 황금의 시대와 은의 시대가 있었다. 이 시대의 인간들은 매우 착하고 성실했기 때문에 신들은 인간과 더불어 땅에 내려와 함께 살았다고 한다. 그러나 세월이 지나 철의 시대가 도래하게 되면서 인간은 매우 부도덕해졌고, 신들은 더 이상 타락한 땅 위에서 인간과 더불어 살 수 없게 되었다.\n" +
                            "\n" +
                            "그러나 더러움을 모르는 정의의 여신 아스트라에아(Astraea)는 인간들에게 사이 좋고 평화롭게 살아가는 일을 꾸준히 가르쳤다. 하지만 그녀의 노력에도 불구하고 인간들은 결국 신은 안중에도 없는 듯 자기 멋대로 설치고 다니게 되었다.\n" +
                            "\n" +
                            "결국 참다못한 신들은 인간에 대한 미련을 버리고 지상을 떠나버렸다. 그래도 아스트라에아는 인간을 내버리지 않고 혼자 남아서 정의를 계속 설교하였는데, 전쟁이 끊임없이 일어나고, 더 이상 지상에 머무를 수 없게 되어 마침내 하늘로 올라가게 된다.\n" +
                            "\n" +
                            "지상에서의 인간 교화에 실패한 아스트라에아였지만, 그녀는 결코 인간을 버릴 수 없었다. 그래서 아스트라에아는 정의를 판단하는 천칭을 들고 하늘의 별자리가 되어 인류에게 정의를 베푸는 일을 계속하고 있다.");
                } // 10
                else if (imgSrc == 11) {
                    birthImg.setImageResource(R.drawable.tstoriesscorpio);
                    content.setText("전갈자리 \n" +
                            "\n" +
                            "(10월23일~11월22일)\n" +
                            "\n" +
                            "그리스 신화에 의하면 이 별자리의 전갈은 사냥꾼 오리온을 죽이기 위해 아폴로(Apollo)신이 풀어놓은 거대한 전갈로 전해진다.\n" +
                            "\n" +
                            "전갈자리(Scorpius)가 뜰 때 서쪽하늘로 오리온자리(Orion)가 지는 것은 이러한 이유에서 비롯되었다고 한다. \n" +
                            "일설에는 전갈을 풀어 놓은 것이 여신 헤라(Hera)라고 한다. 헤라여신이 전갈을 풀어 오리온을 죽이고자 한 것은 오리온이 '자기보다 강한자는 없다!'라고 거만하게 자랑하고 다녀서였다.\n" +
                            "\n" +
                            "어느 이야기가 맞든지 전갈이 오리온을 죽이기 위해 지금도 하늘에서 오리온을 쫓고 있는 것은 사실이다");
                } // 11
                else if (imgSrc == 12) {
                    birthImg.setImageResource(R.drawable.tstoriessagittarius);
                    content.setText("사수자리 \n" +
                            "\n" +
                            "(11월22일~12월21일)\n" +
                            "\n" +
                            "반인반마인 켄타우르(Centaur)가 활 시위를 당기고 있는 모습의 별자리이다.그리스 신화에 의하면 이 별자리의 주인공인 켄타우르는 케이론(Chiron) 이며 그는 아르고호를 타고 황금 양피를 찾아 나선 제자들을 안내하기 위해 자신의 모습을 별자리로 만들었다고 한다.\n" +
                            "\n" +
                            "이자리는 황도 위에 있는 9 번째 별자리로 태양은 해마다 12월하순에서 1월 하순까지 이 별자리를 지난다.우리 나라에서는 흔히 궁수자리 혹은 사수자리로 불러왔다");
                } // 12

                else{
                    content.setText("");
                }
            } // onclick

        });// tStoriesBtn
    } // onCreate


    // 버튼 3(인물 보기)의 이미지 슬라이드
    public void flipperImages(int image){
        ImageView imageview = new ImageView(this);
        imageview.setImageResource(image);

        v_filpper.addView(imageview);
        v_filpper.setFlipInterval(4000);
        v_filpper.setAutoStart(true);

        v_filpper.setInAnimation(this, android.R.anim.slide_in_left);
        v_filpper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    // spinner 메소드 (강제.....)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinnerM) {
            month = position + 1;
//            txt.setText("M: " + month); // 테스트용
        }
        if (parent.getId() == R.id.spinnerD) {
            date = position + 1;
//            txt.setText("D: " + date); // 테스트용
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}
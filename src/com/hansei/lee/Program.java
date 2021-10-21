package com.hansei.lee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class Program {
	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args) throws IOException, FileNotFoundException {
		//myDic의 저장경로
		String path = "C:\\Users\\so021\\Desktop\\자바프로그래밍\\MidterExam_202110105_이일호_2\\myDic.json";
		//Gson 객채 생성
		Gson gson = new Gson();
		//Scanner 객체 생성
		Scanner sc = new Scanner(System.in);
		//ObjectMapper 객체 생성
		ObjectMapper mapper = new ObjectMapper();
		//영단어의 인덱스 값 이니셜라이징
		double num = 0;
		//JSONArray 객체 생성
		JSONArray array = new JSONArray();
		//myDic.json을 읽어들일 FilReader 객체 생성
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"));
		//myDic.json으로부터 내용을 불러와 info에 읽어들이기
		JSONObject info = gson.fromJson(reader, JSONObject.class);
		//num값을 myDic.json의 idx키값으로부터 불러와 num에 대입
		num = (double) info.get("idx");
		//array에 불러온 info값 추가
		array.add(info);
		
		//반복문 시작
		while(true) {
			try {
				//영어사전 메뉴
				System.out.println("================");
				System.out.println("1. 영단어 입력");
				System.out.println("2. 영단어 삭제");
				System.out.println("3. 영단어 검색");
				System.out.println("4. 영단어 전체출력");
				System.out.println("5. 프로그램 초기화(막 누르지 마세요!)");
				System.out.println("0. 저장 및 프로그램 종료");
				System.out.println("================");
				
				//선택변수 sel
				int sel = sc.nextInt();
				
				//sel이 범위 내의 있는지 체크
				if(sel <= 5 && sel >= 0 || sel == 1423) {
					//1번을 선택할 경우
					if(sel == 1) {
						//안내문구
						System.out.println("영단어 입력을 선택하셨습니다.");
						System.out.println("영단어를 입력해주세요.");
						//영단어를 json에 넣을 임시String
						String saveEng = sc.next();
						System.out.println("뜻을 입력해주세요.");
						//위와 같음(뜻을 입력)
						String saveKor = sc.next();
						
						//info에 한글,영어 뜻을 입력(인덱스는 실수 num을 따름)
						info.put("eng" + num, saveEng);
						info.put("kor" + num, saveKor);
						
						array.add(info);
						
						//임시String들의 이니셜라이징 및 인덱스번호 증가
						saveEng = "";
						saveKor = "";
						num++;
						//info에 영단어의 인덱스값을 입력(불러오기 위함)
						info.put("idx", num);
						
					}
					
					//2번을 선택할 경우
					if(sel == 2) {
						//안내메시지
						System.out.println("영단어 삭제를 선택하셨습니다.");
						System.out.println("삭제할 영단어를 입력해주세요.");
						//삭제할 영단어 입력
						String tempEng = sc.next();
						
						//tempEng의 내용이 info에 있는지 확인
						if(info.containsValue(tempEng)) {
							//영단어의 인덱스 최대번호까지 반복
							for(double i = 0; i<num+1; i++) {
								//json형식의 info정보를 String인 tempEng와 비교하기 위한 String화
								String jsonString = info.get("eng" + i).toString();
								//제대로 작동하는지 체크
								//System.out.println(jsonString);
								//위 String(jsonString)과 tempEng가 같다면
								if(jsonString.equals(tempEng)) {
									//영단어와 그 뜻을 출력
									System.out.println(info.get("eng" + (double)i));
									System.out.println(info.get("kor" + (double)i));
									//정말 삭제할 것인지 확인
									System.out.println("위 단어를 정말로 삭제하시겠습니까? (0 : 취소, 1 : 삭제)");
									//선택지 int로 받기
									int del_sel = sc.nextInt();
									//삭제하기(1)을 선택했다면
									if(del_sel == 1) {
										//영단어와 그 뜻을 삭제
										info.remove("eng" + (double)i);
										info.remove("kor" + (double)i);
										//삭제안내
										System.out.println("단어를 삭제하였습니다.");
										
										//tempEng초기화
										tempEng = "";
									//취소(0)을 선택
									}else if(del_sel == 0) {
										//취소안내
										System.out.println("취소하였습니다.");
										//tempEng초기화
										tempEng = "";
									//그 외에 번호 선택
									}else {
										//오류 안내
										System.out.println("잘못된 선택지입니다.");
										//tempEng초기화
										tempEng = "";
									}
									
								}
							}
						//info에 tempEng가 없을 경우
						}else {
							//오류 안내
							System.out.println("입력되어있지 않는 단어입니다.");
							//tempEng초기화
							tempEng = "";
						}
					
					}
					
					//3번을 선택할 경우
					if(sel == 3) {
						//안내 메시지
						System.out.println("영단어 검색을 선택하셨습니다.");
						System.out.println("검색할 영단어를 검색해주세요.");
						//검색할 영단어 입력
						String tempEng = sc.next();
						
						//tempEng의 내용이 info에 있는지 확인
						if(info.containsValue(tempEng)) {
							//영단어의 인덱스 최대번호까지 반복
							for(double i = 0; i<num+1; i++) {
								//json형식의 info정보를 String인 tempEng와 비교하기 위한 String화
								String jsonString = info.get("eng" + i).toString();
								//제대로 작동하는지 체크
								//System.out.println(jsonString);
								//위 String(jsonString)과 tempEng가 같다면
								if(jsonString.equals(tempEng)) {
									//해당 인덱스에 해당하는 영단어와 그 뜻을 출력
									System.out.println("영어 : " + info.get("eng" + (double)i));
									System.out.println("뜻 : " + info.get("kor" + (double)i));
									//tempEng초기화
									tempEng = "";
								}
							}
						//info의 tempString이 없을경우
						}else {
							//오류 안내
							System.out.println("입력되어있지 않는 단어입니다.");
							//tempEng초기화
							tempEng = "";
						}
						
					}
					
					//4번을 선택할 경우
					if(sel == 4) {
						//영단어의 인덱스 최대번호까지 반복
						for(int i = 0; i<num+1; i++) {
							//만일 그 인덱스가 비어있지 않다면
							if(info.get("eng" + (double)i) != null) {
								//영단어와 그 뜻 출력
								System.out.println("================");
								System.out.println("영어 : " + info.get("eng" + (double)i));
								System.out.println("뜻 : " + info.get("kor" + (double)i));
								System.out.println("================");
							}
						}
					}
					
					//5번을 선택할 경우
					if(sel == 5) {
						//info의 모든값 제거
						info.clear();
						//인덱스 값 0으로 이니셜라이징 및 생성
						info.put("idx", 0);
						//인덱스 값 0으로 초기화
						num = 0;
					}
					
					//0번을 선택할 경우
					if(sel == 0) {
						//안내 메시지
						System.out.println("저장 후 영어사전을 종료합니다.");
						try {
							//myDic.json파일에 "예쁘게" 쓰기(출력)
							mapper.writerWithDefaultPrettyPrinter().writeValue(new File("myDic.json"), info);
						//예기치 못한 에러 발생시
						}catch(Exception e) {
							//에러 안내
							System.out.println("에러가 발생하였습니다.");
							//에러메시지 출력
							e.printStackTrace();
						}
						//반복문 종료
						break;
					}
					/*모니터링용
					 *인덱스값, array값, info값 제대로 로드됐는지, 이니셜라이징 됐는지 확인용*/
					if(sel == 1423) {
						System.out.println(num);
						System.out.println(array);
						System.out.println(info);
					}
					/*info 안의 모든 내용 삭제 및 인덱스값 초기화하기용
					if(sel == 4545) {
						info.clear();
						info.put("idx", 0);
						num = 0;
					}*/
				//범위 이외에 번호를 선택한 경우
				}else {
					//안내 메시지
					System.out.println("잘못된 번호입니다.");
				}
			//예기치 못한 오류 발생시
			}catch(Exception e) {
				//에러 메시지 출력
				e.printStackTrace();
			}
		}
	}
}

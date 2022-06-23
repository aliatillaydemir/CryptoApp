# CRYPTOAPP

CryptoApp bir tür kripto takip uygulamasıdır. Uygulama 3 temel parçadan oluşur. Bu uygulama sayesinde kripto paraları canlı verilerle takip edebilir(api'ın tanıdığı 
yenileme süresince), listelenmis bu kripto paraları dinamik olarak arama çubuğunda aratabilir ve ayrıntıları üzerine tıklayarak görebilirsiniz. Proje MVVM mimarisine
uygun bir biçimde gerçekleştirilmiştir.

- Projede kotlin dili ve Jetpack Compose tool'u kullanılmıştır.

## **Kullanılan Teknolojiler**

- Nomics
- Retrofit
- Coroutines
- Coil
- Dependency Injection(Dagger-Hilt)
- Splashscreen
- Flow(StateFlow)

### Nomics:
Nomics kripto pazarına ulaşmak için kullanılan api'dır. Endpoint üzerinden retrofit ve diğer teknolojilerle birlikte veriler çekilmiştir.

### Retrofit:
Rest istemci. Bu network kütüphanesi Nomics'e ulaşırken kullanılmıştır.

### Coroutines:
Asenkron çalışma mantığı için kullanılmıştır.

### Coil:
Fotoğrafların download edilmesi için kullanılmıştır.

### Dagger Hilt:
Dependency Injection işlemi için kullanılmıştır.

### splashscreen:
Android kütüphanesidir, splash screen için kullanılmıştır.

### Flow:
Splash screen ekranının kullanıcı ara yüzünü bloklamaması için coroutine scope ile kullanılmıştır.

- Dinamik(composable) gösterim için Stateler kullanılmıştır.

-----------------------------------------------------------------------------------------------------------------------------------------------

## Kullanıcı Arayüzü 

1. Giriş yapıldığında gözüken splash screen.
<img width="249" alt="Screenshot_1" src="https://user-images.githubusercontent.com/43906043/174165582-355db9c0-6033-438a-ab60-eafa360c94e9.png">


2. Ana sayfa olan kripto listeleme sayfası.
<img width="252" alt="Screenshot_2" src="https://user-images.githubusercontent.com/43906043/174165591-56a59661-0d59-4277-aca1-68f16eb39269.png">


3. Ana sayfada listeleme yapılmasını sağlayan arama çubuğu.
<img width="249" alt="Screenshot_3" src="https://user-images.githubusercontent.com/43906043/174165595-325273b9-637b-46cd-97d7-edaf642ce8cd.png">


4. Herhangi bir kriptoya tıklandığında çıkacak olan ayrıntı sayfası(compose'u).
<img width="251" alt="Screenshot_4" src="https://user-images.githubusercontent.com/43906043/174165605-50a14d4f-e6c0-4efd-a953-fe9443eafc53.png">

------------------------
Not: Nomics Api üzerinden çekilen görüntü verileri bazen işlemeyebiliyor. Bu nomics ile ilgili bir problemdir. Belli aralıklarla değişim gerçekleşebilmektedir.

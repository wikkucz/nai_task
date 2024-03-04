# nai_task
Dane wejściowe: 

Dane treningowe – plik iris_training.txt
Dane testowe – plik iris_test.txt

Program musi wczytać dane z podanego pliku tekstowego. Zakładamy, ze:

•	Atrybut decyzyjny znajduje się w ostatniej kolumnie.

•	Wszystkie atrybuty poza decyzyjnym są numeryczne.


Program musi akceptować dowolną liczbę atrybutów warunkowych, tzn. nie może zakładać, że ich jest ustalona liczba. 

Następnie algorytmem delty trenujemy perceptron, który rozróżnia klasę Iris-setosa od dwóch pozostałych klas. 

Testujemy go potem na danych ze zbioru testowego. 

Jako wynik ma wypisać liczbę prawidłowo zaklasyfikowanych przykładów oraz dokładność eksperymentu wyrażoną w procentach.

Program musi umożliwiać wielokrotne ręczne wpisanie wektora atrybutów i wypisać dla takiego wektor jego wynik klasyfikacji.

Nie można używać żadnych bibliotek ML, wszystko ma być zaimplementowane od zera w pętlach, if-ach, wyjście perceptronu trzeba liczyć za pomocą działań arytmetycznych, etc.

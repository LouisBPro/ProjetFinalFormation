import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Plat } from "../model/plat";

@Injectable({
  providedIn: "root",
})
export class PlatService {
  url: string = "http://localhost:8080/hashi/api/plat";
  constructor(private http: HttpClient) {}

  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorization: "Basic " + sessionStorage.getItem("token"),
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
    });
  }
  public allPlats(): Observable<Plat[]> {
    return this.http.get<Plat[]>(this.url, { headers: this.httpHeaders });
  }
  public delete(id: number): Observable<any> {
    return this.http.delete(this.url + "/" + id, { headers: this.httpHeaders });
  }
  public getById(id: number): Observable<Plat> {
    return this.http.get<Plat>(this.url + "/" + id, {
      headers: this.httpHeaders,
    });
  }
  public update(plat: Plat): Observable<Plat> {
    console.log(plat);
    return this.http.put<Plat>(this.url + "/" + plat.id, plat, {
      headers: this.httpHeaders,
    });
  }
  public insert(plat: Plat): Observable<Plat> {
    console.log(plat);
    const o = {
      nom: plat.nom,
      description: plat.description,
      prix: plat.prix,
      photo: plat.photo,
    };
    console.log(o);
    return this.http.post<Plat>(this.url, o, { headers: this.httpHeaders });
  }
  public Upload(plat: Plat, selectedFile: any) {
    const uploadData = new FormData();
    uploadData.append("myFile", selectedFile, selectedFile.name);
    return this.http.post(
      "http://localhost:8080/api/plat/update/" + plat.id,
      uploadData,
      {
        headers: this.httpHeaders,
      }
    );
  }
}

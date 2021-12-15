import { LigneCarte } from "./ligne-carte";
import { Adresse } from "src/app/model/adresse";
import { Gerant } from "./gerant";
import { Cuisinier } from "./cuisinier";
export class Restaurant {
  constructor(
    private _adresse?: Adresse | undefined,
    private _ligneCarte?: LigneCarte[] | undefined,
    private _id?: number | undefined,
    private _nom?: string | undefined,
    private _gerant?: Gerant | undefined,
    private _cuisiniers?: Cuisinier[] | undefined
    // private _codePostal?: string | undefined, // private _numero?: number | undefined, // private _rue?: string | undefined, // private _ville?: string | undefined
  ) {}

  /**
   * Getter id
   * @return {number}
   */
  public get id(): number | undefined {
    return this._id;
  }

  /**
   * Getter nom
   * @return {string}
   */
  public get nom(): string | undefined {
    return this._nom;
  }

  /**
   * Setter id
   * @param {number} value
   */
  public set id(value: number | undefined) {
    this._id = value;
  }

  /**
   * Setter nom
   * @param {string} value
   */
  public set nom(value: string | undefined) {
    this._nom = value;
  }
  /**
   * Getter adresse
   * @return {Adresse}
   */
  public get adresse(): Adresse | undefined {
    return this._adresse;
  }

  /**
   * Setter adresse
   * @param {Adresse} value
   */
  public set adresse(value: Adresse | undefined) {
    this._adresse = value;
  }
  /**
   * Getter ligneCarte
   * @return {LigneCarte}
   */
  public get ligneCarte(): LigneCarte[] | undefined {
    return this._ligneCarte;
  }

  /**
   * Setter ligneCarte
   * @param {LigneCarte} value
   */
  public set ligneCarte(value: LigneCarte[] | undefined) {
    this._ligneCarte = value;
  }

  public get gerant(): Gerant | undefined {
    return this._gerant;
  }

  public set gerant(value: Gerant | undefined) {
    this._gerant = value;
  }

  public get cuisiniers(): Cuisinier[] | undefined {
    return this._cuisiniers;
  }

  public set cuisiniers(value: Cuisinier[] | undefined) {
    this._cuisiniers = value;
  }

  public addCuisinier(value : Cuisinier){
    this.cuisiniers?.push(value);
  }
  // /**
  //  * Getter numero
  //  * @return {number}
  //  */
  // public get numero(): number | undefined {
  //   return this._numero;
  // }

  // /**
  //  * Getter rue
  //  * @return {string}
  //  */
  // public get rue(): string | undefined {
  //   return this._rue;
  // }

  // /**
  //  * Getter codePostal
  //  * @return {string}
  //  */
  // public get codePostal(): string | undefined {
  //   return this._codePostal;
  // }

  // /**
  //  * Getter ville
  //  * @return {string}
  //  */
  // public get ville(): string | undefined {
  //   return this._ville;
  // }

  // /**
  //  * Setter numero
  //  * @param {number} value
  //  */
  // public set numero(value: number | undefined) {
  //   this._numero = value;
  // }

  // /**
  //  * Setter rue
  //  * @param {string} value
  //  */
  // public set rue(value: string | undefined) {
  //   this._rue = value;
  // }

  // /**
  //  * Setter codePostal
  //  * @param {string} value
  //  */
  // public set codePostal(value: string | undefined) {
  //   this._codePostal = value;
  // }

  // /**
  //  * Setter ville
  //  * @param {string} value
  //  */
  // public set ville(value: string | undefined) {
  //   this._ville = value;
  // }
}
